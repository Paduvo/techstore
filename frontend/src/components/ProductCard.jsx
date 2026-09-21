function ProductCard({ produto, onAdicionar }) {
  return (
    <article className="product-card">
      <button className="favorite-button">
        ♡
      </button>

          <div className="product-image">
      {produto.imagem ? (
        <img src={`/images/${produto.imagem}`} alt={produto.nome} />
      ) : (
        <span>💻</span>
      )}
    </div>

      <div className="product-info">
        <span className="product-category">
          TECNOLOGIA
        </span>

        <h3>{produto.nome}</h3>

        <p>{produto.descricao}</p>

        <div className="product-bottom">
          <strong>
            R$ {Number(produto.preco).toFixed(2).replace(".", ",")}
          </strong>

          <button
            className="add-cart"
            onClick={() => onAdicionar(produto)}
          >
            🛒
          </button>
        </div>
      </div>
    </article>
  );
}

export default ProductCard;