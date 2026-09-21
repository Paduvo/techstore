function Header({ quantidadeCarrinho, onAbrirCarrinho }) {
  const token = localStorage.getItem("token");
  const usuarioLogado = Boolean(token);

  function sair() {
    localStorage.removeItem("token");
    window.location.href = "/login";
  }

  return (
    <header className="header">
      <div className="container header-content">
        <a href="/" className="logo">
          <span>⚡</span>
          TechStore
        </a>

        <div className="search">
          <input
            type="text"
            placeholder="Buscar produtos, marcas, categorias..."
          />

          <button>⌕</button>
        </div>

        <nav className="nav">
          <a href="/" className="active">
            Início
          </a>

          <a href="#produtos">Produtos</a>

          <a href="#categorias">Categorias</a>

          <a href="#ofertas">Ofertas</a>
        </nav>

        <div className="header-actions">
          {usuarioLogado ? (
            <button className="login-button" onClick={sair}>
              👤 Sair
            </button>
          ) : (
            <button
              className="login-button"
              onClick={() => (window.location.href = "/login")}
            >
              👤 Entrar
            </button>
          )}

          <button className="cart-button" onClick={onAbrirCarrinho}>
            🛒 Carrinho
            <small>{quantidadeCarrinho || 0}</small>
          </button>
        </div>
      </div>
    </header>
  );
}

export default Header;
