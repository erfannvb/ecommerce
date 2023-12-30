function addToCart(pId, pTitle, pPrice) {

    let cart = localStorage.getItem("cart");
    if (cart == null) {

        let productsArray = [];

        let productObj = {
            productId: pId,
            productTitle: pTitle,
            productPrice: pPrice,
            productQuantity: 1
        }

        productsArray.push(productObj);

        localStorage.setItem("cart", JSON.stringify(productsArray));

    }

}