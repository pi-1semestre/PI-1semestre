document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('formLogin');

  form.addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    fetch('http://127.0.0.1:8080/api/v1/administradores/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ emailAdm: email, senha: senha })
    })
    .then(res => {
      if (!res.ok) throw new Error("Usuário ou senha incorretos!");
      return res.json();
    })
    .then(admin => {
      if (admin) {
        window.location.href = 'cadastro_animal.html';
      } else {
        alert('Usuário ou senha incorretos!');
      }
    })
    .catch(err => {
      console.error(err);
      alert('Erro ao realizar login: ' + err.message);
    });
  });
});
