function Cart({
  carrinho,
  alterarQuantidade,
  removerDoCarrinho,
  onFinalizarCompra,
  finalizandoCompra,
}) {
  const total = carrinho.reduce(
    (valor, produto) => valor + produto.preco * produto.quantidade,
    0,
  );

  return (
    <section className="cart">
      <div className="container">
        <div className="section-heading">
          <span>SEU PEDIDO</span>
          <h2>Seu carrinho</h2>
        </div>

        {carrinho.length === 0 ? (
          <div className="cart-empty">
            <span>🛒</span>
            <h3>Seu carrinho está vazio</h3>
            <p>Adicione produtos para começar sua compra.</p>
          </div>
        ) : (
          <>
            <div className="cart-items">
              {carrinho.map((produto) => (
                <div className="cart-item" key={produto.id}>
                  <div className="cart-item-info">
                    <h3>{produto.nome}</h3>

                    <p>
                      R$ {Number(produto.preco).toFixed(2).replace(".", ",")}{" "}
                      por unidade
                    </p>
                  </div>

                  <div className="cart-item-actions">
                    <div className="quantity-control">
                      <button
                        onClick={() => alterarQuantidade(produto.id, -1)}
                        disabled={finalizandoCompra}
                      >
                        −
                      </button>

                      <span>{produto.quantidade}</span>

                      <button
                        onClick={() => alterarQuantidade(produto.id, 1)}
                        disabled={finalizandoCompra}
                      >
                        +
                      </button>
                    </div>

                    <strong>
                      R${" "}
                      {(produto.preco * produto.quantidade)
                        .toFixed(2)
                        .replace(".", ",")}
                    </strong>

                    <button
                      className="remove-cart"
                      onClick={() => removerDoCarrinho(produto.id)}
                      disabled={finalizandoCompra}
                    >
                      Remover
                    </button>
                  </div>
                </div>
              ))}
            </div>

            <div className="cart-total">
              <span>Total da compra</span>

              <strong>R$ {total.toFixed(2).replace(".", ",")}</strong>
            </div>

            <button
              className="checkout-button"
              onClick={onFinalizarCompra}
              disabled={finalizandoCompra}
            >
              {finalizandoCompra
                ? "Finalizando compra..."
                : "Finalizar compra →"}
            </button>
          </>
        )}
      </div>
    </section>
  );
}

export default Cart;
