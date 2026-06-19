const API_BASE = 'http://localhost:5000/api'; // using local Flask API

async function login(ev) {
  ev.preventDefault();
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  // For now do client-side demo login
  if (username === 'admin' && password === 'admin') {
    window.location.href = 'pages/dashboard.html';
    return;
  }

  // If backend available, you can POST to `${API_BASE}/login`
  alert('Invalid demo credentials. Use admin / admin');
}

// Students
async function submitAddStudent(ev) {
  ev.preventDefault();
  const name = document.getElementById('s_name').value;
  const email = document.getElementById('s_email').value;
  const dept = document.getElementById('s_dept').value;

  try {
    const res = await fetch(`${API_BASE}/students`, {
      method: 'POST', headers: {'Content-Type':'application/json'},
      body: JSON.stringify({name,email,department:dept})
    });
    if (res.ok) { alert('Student added'); document.getElementById('addStudentForm').reset(); }
    else alert('Failed to add student');
  } catch(e) { alert(e.message); }
}

async function loadStudents() {
  try {
    const res = await fetch(`${API_BASE}/students`);
    const data = await res.json();
    const tbody = document.getElementById('studentsTbody');
    if(!tbody) return;
    tbody.innerHTML = '';
    data.forEach(s => {
      const tr = document.createElement('tr');
      tr.innerHTML = `<td>${s.id}</td><td>${s.name}</td><td>${s.email}</td><td>${s.department}</td><td class="table-actions"><button class="btn btn-sm btn-danger" onclick="deleteStudent(${s.id})">Delete</button></td>`;
      tbody.appendChild(tr);
    });
  } catch(e) { console.error(e); }
}

async function deleteStudent(id) {
  if(!confirm('Delete student?')) return;
  try {
    const res = await fetch(`${API_BASE}/students/${id}`, { method: 'DELETE' });
    if (res.ok) loadStudents(); else alert('Delete failed');
  } catch(e) { alert(e.message); }
}

// Courses
async function submitAddCourse(ev) {
  ev.preventDefault();
  const name = document.getElementById('c_name').value;
  const credits = parseInt(document.getElementById('c_credits').value,10);
  try {
    const res = await fetch(`${API_BASE}/courses`, { method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify({course_name:name,credits}) });
    if(res.ok) { alert('Course added'); document.getElementById('addCourseForm').reset(); }
    else alert('Failed to add course');
  } catch(e){ alert(e.message); }
}

async function loadCourses() {
  try {
    const res = await fetch(`${API_BASE}/courses`);
    const data = await res.json();
    const tbody = document.getElementById('coursesTbody');
    if(!tbody) return;
    tbody.innerHTML = '';
    data.forEach(c => {
      const tr = document.createElement('tr');
      tr.innerHTML = `<td>${c.id}</td><td>${c.course_name}</td><td>${c.credits}</td><td class="table-actions"><button class="btn btn-sm btn-danger" onclick="deleteCourse(${c.id})">Delete</button></td>`;
      tbody.appendChild(tr);
    });
  } catch(e){ console.error(e); }
}

async function deleteCourse(id){ if(!confirm('Delete course?')) return; try{ const res=await fetch(`${API_BASE}/courses/${id}`,{method:'DELETE'}); if(res.ok) loadCourses(); else alert('Delete failed'); } catch(e){ alert(e.message); } }

// Registration
async function submitRegisterCourse(ev){ ev.preventDefault(); const sid=parseInt(document.getElementById('r_sid').value,10); const cid=parseInt(document.getElementById('r_cid').value,10); try{ const res=await fetch(`${API_BASE}/registrations`,{method:'POST',headers:{'Content-Type':'application/json'}, body: JSON.stringify({student_id:sid,course_id:cid})}); if(res.ok){ alert('Registered'); document.getElementById('registerForm').reset(); } else alert('Registration failed'); } catch(e){ alert(e.message); } }

// Utility: auto-load tables when pages include them
document.addEventListener('DOMContentLoaded', ()=>{ if(document.getElementById('studentsTbody')) loadStudents(); if(document.getElementById('coursesTbody')) loadCourses(); const addS = document.getElementById('addStudentForm'); if(addS) addS.addEventListener('submit', submitAddStudent); const addC = document.getElementById('addCourseForm'); if(addC) addC.addEventListener('submit', submitAddCourse); const reg = document.getElementById('registerForm'); if(reg) reg.addEventListener('submit', submitRegisterCourse); });
