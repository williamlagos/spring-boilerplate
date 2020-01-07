package com.example;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CartResource {

  @Autowired
  private CartRepository repository;

  @Autowired
  private ProductRepository products;

  @Autowired
  private CustomerRepository customers;
  /*
   * Construtor do CartResource, preparando uma lista de compras
   */
  public CartResource(CartRepository repository) {
    this.repository = repository;
  }

  @RequestMapping(value = "/compras/", 
  method = RequestMethod.POST)
  public Cart criarCompra(@RequestBody Cart cart) {
    Product product = this.products.findById(
      cart.getProduct().getId()).get();
    Customer customer = this.customers.findById(
      cart.getCustomer().getId()).get();
    return this.repository.save(new Cart(product, customer));
  }

  @RequestMapping(value="/compras/{id}", 
  method=RequestMethod.PUT)
  public void alterarCompra(@PathVariable Long id,
  @RequestBody Cart produtoParam) {
      Cart compra = this.repository.findById(id).get();
      compra.setProduct(this.products.findById(
        produtoParam.getProduct().getId()).get());
      compra.setCustomer(this.customers.findById(
        produtoParam.getCustomer().getId()).get());
      this.repository.save(compra);
  }

  @RequestMapping(value="/compras/finalizar",
  method=RequestMethod.POST)
  public Map<String,String> finalizarCompras() {
    this.repository.deleteAll();
    Map<String,String> res = new HashMap<>();
    res.put("Mensagem", "Compra finalizada");
    return res;
  }

  /**
   * Metodo de requisicao do tipo GET, para uma lista
   * @return lista de compras, filtrados ou nao
   */
  @RequestMapping(value = "/compras/", method = RequestMethod.GET)
  public Iterable<Cart> buscarCompras() {
    return this.repository.findAll();
  }

  /**
   * Metodo de requisicao do tipo GET, para um item
   * @param id identificador ou indice da colecao das compras
   * @return item de produto unico
   */
  @RequestMapping(value = "/compras/{id}", method = RequestMethod.GET)
  public Optional<Cart> buscarCompra(@PathVariable Long id) {
    return this.repository.findById(id);
  }
  
  /**
   * Metodo de requisicao do tipo DELETE, para remover um item
   * @param id identificador ou indice da colecao das compras
   */
  @RequestMapping(value = "/compras/{id}", method = RequestMethod.DELETE)
  public void removerCompra(@PathVariable Long id) {
    this.repository.deleteById(id);
  }
}
