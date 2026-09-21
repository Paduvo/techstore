import { useEffect, useState } from "react";

import Header from "../components/Header";
import Hero from "../components/Hero";
import CategoryCard from "../components/CategoryCard";
import ProductCard from "../components/ProductCard";
import Cart from "../components/Cart";

import { buscarProdutos, criarPedido } from "../services/api";

function Home() {
  const [produtos, setProdutos] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState("");
  const [carrinho, setCarrinho] = useState([]);
  const [finalizandoCompra, setFinalizandoCompra] = useState(false);

  useEffect(() => {
    carregarProdutos();
  }, []);

  async function carregarProdutos() {
    try {
      const dados = await buscarProdutos();
      setProdutos(dados);
      setErro("");
    } catch {
      setErro("Não foi possível carregar os produtos.");
    } finally {
      setCarregando(false);
    }
  }

  function adicionarAoCarrinho(produto) {
    setCarrinho((carrinhoAtual) => {
      const produtoExistente = carrinhoAtual.find(
        (item) => item.id === produto.id,
      );

      if (produtoExistente) {
        return carrinhoAtual.map((item) =>
          item.id === produto.id
            ? { ...item, quantidade: item.quantidade + 1 }
            : item,
        );
      }

      return [
        ...carrinhoAtual,
        {
          ...produto,
          quantidade: 1,
        },
      ];
    });
  }

  function alterarQuantidade(id, quantidade) {
    setCarrinho((carrinhoAtual) =>
      carrinhoAtual
        .map((produto) =>
          produto.id === id
            ? {
                ...produto,
                quantidade: produto.quantidade + quantidade,
              }
            : produto,
        )
        .filter((produto) => produto.quantidade > 0),
    );
  }

  function removerDoCarrinho(id) {
    setCarrinho((carrinhoAtual) =>
      carrinhoAtual.filter((produto) => produto.id !== id),
    );
  }

  async function finalizarCompra() {
    if (carrinho.length === 0) {
      return;
    }

    const token = localStorage.getItem("token");

    if (!token) {
      window.location.href = "/login";
      return;
    }

    try {
      setFinalizandoCompra(true);

      const pedido = await criarPedido(carrinho);

      setCarrinho([]);

      await carregarProdutos();

      alert(
        `Pedido #${pedido.id} realizado com sucesso!\nTotal: R$ ${Number(
          pedido.valorTotal,
        )
          .toFixed(2)
          .replace(".", ",")}`,
      );
    } catch (error) {
      alert(error.message || "Não foi possível finalizar a compra.");
    } finally {
      setFinalizandoCompra(false);
    }
  }

  return (
    <>
      <Header
        quantidadeCarrinho={carrinho.reduce(
          (total, produto) => total + produto.quantidade,
          0,
        )}
        onAbrirCarrinho={() => {}}
      />

      <main>
        <Hero />

        <Cart
          carrinho={carrinho}
          alterarQuantidade={alterarQuantidade}
          removerDoCarrinho={removerDoCarrinho}
          onFinalizarCompra={finalizarCompra}
          finalizandoCompra={finalizandoCompra}
        />

        <section className="categories" id="categorias">
          <div className="container">
            <div className="section-heading">
              <span>ENCONTRE O QUE PRECISA</span>
              <h2>Explore por categoria</h2>
            </div>

            <div className="categories-grid">
              <CategoryCard icon="💻" title="Notebooks" />
              <CategoryCard icon="🖥️" title="Monitores" />
              <CategoryCard icon="🎧" title="Áudio" />
              <CategoryCard icon="⌨️" title="Periféricos" />
            </div>
          </div>
        </section>

        <section className="products" id="produtos">
          <div className="container">
            <div className="section-heading">
              <span>SELEÇÃO DA TECHSTORE</span>
              <h2>Produtos em destaque</h2>
            </div>

            {carregando && <p>Carregando produtos...</p>}

            {erro && <p>{erro}</p>}

            {!carregando && !erro && (
              <div className="products-grid">
                {produtos.map((produto) => (
                  <ProductCard
                    key={produto.id}
                    produto={produto}
                    onAdicionar={adicionarAoCarrinho}
                  />
                ))}
              </div>
            )}
          </div>
        </section>
      </main>
    </>
  );
}

export default Home;
