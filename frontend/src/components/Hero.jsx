function Hero() {
  return (
    <section className="hero">
      <div className="container hero-content">

        <div className="hero-text">

          <span className="hero-label">
            TECNOLOGIA PARA O SEU MUNDO
          </span>

          <h1>
            Seu setup começa
            <strong>aqui.</strong>
          </h1>

          <p>
            Encontre notebooks, periféricos, monitores e
            acessórios para transformar sua experiência.
          </p>

          <div className="hero-buttons">
            <a href="#produtos" className="primary-button">
              Explorar produtos
              <span>→</span>
            </a>

            <a href="#categorias" className="secondary-button">
              Ver categorias
            </a>
          </div>

          <div className="hero-info">

            <div>
              <strong>100%</strong>
              <span>Compra segura</span>
            </div>

            <div>
              <strong>+500</strong>
              <span>Produtos</span>
            </div>

            <div>
              <strong>24h</strong>
              <span>Atendimento</span>
            </div>

          </div>

        </div>

        <div className="hero-visual">

          <div className="hero-circle"></div>

          <div className="setup-card">
            <div className="setup-monitor">
              <span>TECH</span>
            </div>

            <div className="setup-base"></div>

            <div className="setup-keyboard">
              <span></span>
              <span></span>
              <span></span>
              <span></span>
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </div>
          </div>

          <div className="floating-card floating-top">
            🔥
            <div>
              <strong>Mais vendidos</strong>
              <small>Produtos em alta</small>
            </div>
          </div>

          <div className="floating-card floating-bottom">
            ⚡
            <div>
              <strong>Performance</strong>
              <small>Feito para você</small>
            </div>
          </div>

        </div>

      </div>
    </section>
  );
}

export default Hero;