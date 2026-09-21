import { useState } from "react";
import { fazerLogin } from "../services/api";

function Login() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");
  const [carregando, setCarregando] = useState(false);

  async function handleLogin(event) {
    event.preventDefault();

    setErro("");
    setCarregando(true);

    try {
      const dados = await fazerLogin(email, senha);

      localStorage.setItem("token", dados.token);

      window.location.href = "/";
    } catch {
      setErro("E-mail ou senha inválidos.");
    } finally {
      setCarregando(false);
    }
  }

  return (
    <main className="login-page">
      <div className="login-card">
        <div className="login-logo">
          <span>⚡</span>
          TechStore
        </div>

        <div className="login-heading">
          <span>ACESSO À CONTA</span>
          <h1>Bem-vindo de volta</h1>
          <p>Entre na sua conta para continuar sua compra.</p>
        </div>

        <form onSubmit={handleLogin}>
          <div className="form-group">
            <label htmlFor="email">E-mail</label>

            <input
              id="email"
              type="email"
              placeholder="seu@email.com"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="senha">Senha</label>

            <input
              id="senha"
              type="password"
              placeholder="Digite sua senha"
              value={senha}
              onChange={(event) => setSenha(event.target.value)}
              required
            />
          </div>

          {erro && <p className="login-error">{erro}</p>}

          <button type="submit" className="login-submit" disabled={carregando}>
            {carregando ? "Entrando..." : "Entrar →"}
          </button>
        </form>

        <p className="login-register">
          Ainda não possui uma conta?
          <a href="#"> Criar conta</a>
        </p>
      </div>
    </main>
  );
}

export default Login;
