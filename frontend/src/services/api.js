const API_URL = "http://localhost:8080";

export async function buscarProdutos() {
  const resposta = await fetch(`${API_URL}/produtos`);

  if (!resposta.ok) {
    throw new Error("Não foi possível carregar os produtos.");
  }

  return resposta.json();
}

export async function fazerLogin(email, senha) {
  const resposta = await fetch(`${API_URL}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email,
      senha,
    }),
  });

  if (!resposta.ok) {
    throw new Error("E-mail ou senha inválidos.");
  }

  return resposta.json();
}

export async function criarPedido(carrinho) {
  const token = localStorage.getItem("token");

  if (!token) {
    throw new Error("Usuário não autenticado.");
  }

  const resposta = await fetch(`${API_URL}/pedidos`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({
      itens: carrinho.map((produto) => ({
        produtoId: produto.id,
        quantidade: produto.quantidade,
      })),
    }),
  });

  if (!resposta.ok) {
    throw new Error("Não foi possível finalizar a compra.");
  }

  return resposta.json();
}
