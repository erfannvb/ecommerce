function addToCart(pId, pTitle, pPrice) {

    let cart = localStorage.getItem("cart");
    if (cart == null) {

        let products = [];

        let productObj = {
            productId: pId,
            productTitle: pTitle,
            productPrice: pPrice,
            productQuantity: 1
        }

        products.push(productObj);

        localStorage.setItem("cart", JSON.stringify(products));

    }

}