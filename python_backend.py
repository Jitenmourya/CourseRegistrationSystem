from flask import Flask, request, jsonify
import psycopg2
import os

app = Flask(__name__)

DB_URL = os.environ.get('DB_URL') or 'dbname=course_db user=postgres password=Jiten@12345 host=localhost'

def get_conn():
    return psycopg2.connect(DB_URL)

@app.route('/api/students', methods=['GET'])
def get_students():
    conn = get_conn()
    cur = conn.cursor()
    cur.execute('SELECT id,name,email,department FROM students ORDER BY id')
    rows = cur.fetchall()
    cur.close(); conn.close()
    return jsonify([{'id':r[0],'name':r[1],'email':r[2],'department':r[3]} for r in rows])

@app.route('/api/students', methods=['POST'])
def add_student():
    data = request.get_json()
    name = data.get('name')
    email = data.get('email')
    dept = data.get('department')
    conn = get_conn(); cur = conn.cursor()
    try:
        cur.execute('INSERT INTO students(name,email,department) VALUES(%s,%s,%s)', (name,email,dept))
        conn.commit()
        return jsonify({'status':'ok'})
    except Exception as e:
        conn.rollback(); return jsonify({'error':str(e)}), 500
    finally:
        cur.close(); conn.close()

@app.route('/api/students/<int:sid>', methods=['DELETE'])
def delete_student(sid):
    conn = get_conn(); cur = conn.cursor()
    cur.execute('DELETE FROM students WHERE id=%s', (sid,))
    conn.commit(); cur.close(); conn.close()
    return jsonify({'status':'ok'})

@app.route('/api/courses', methods=['GET'])
def get_courses():
    conn = get_conn(); cur = conn.cursor(); cur.execute('SELECT id,course_name,credits FROM courses ORDER BY id')
    rows = cur.fetchall(); cur.close(); conn.close()
    return jsonify([{'id':r[0],'course_name':r[1],'credits':r[2]} for r in rows])

@app.route('/api/courses', methods=['POST'])
def add_course():
    data = request.get_json(); name = data.get('course_name'); credits = data.get('credits')
    conn = get_conn(); cur = conn.cursor()
    try:
        cur.execute('INSERT INTO courses(course_name,credits) VALUES(%s,%s)', (name,credits)); conn.commit(); return jsonify({'status':'ok'})
    except Exception as e:
        conn.rollback(); return jsonify({'error':str(e)}),500
    finally:
        cur.close(); conn.close()

@app.route('/api/registrations', methods=['POST'])
def register_course():
    data = request.get_json(); sid = data.get('student_id'); cid = data.get('course_id')
    conn = get_conn(); cur = conn.cursor()
    try:
        cur.execute('INSERT INTO registration(student_id,course_id) VALUES(%s,%s)', (sid,cid)); conn.commit(); return jsonify({'status':'ok'})
    except Exception as e:
        conn.rollback(); return jsonify({'error':str(e)}),500
    finally:
        cur.close(); conn.close()

if __name__ == '__main__':
    # enable CORS manually for simple dev
    from flask_cors import CORS
    CORS(app)
    app.run(host='0.0.0.0', port=5000)
