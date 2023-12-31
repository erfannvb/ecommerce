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

        console.log("Product added for the first time.");

    } else {

        let productCart = JSON.parse(cart);

        let existingProduct = productCart.find((item) => item.productId === pId);

        if (existingProduct) {

            // have to add the quantity
            existingProduct.productQuantity += 1;

            productCart.map((item) => {
                if (item.productId === existingProduct.productId) {
                    item.productQuantity = existingProduct.productQuantity;
                }
            })

            localStorage.setItem("cart", JSON.stringify(productCart));

            console.log("Product quantity increased.");

        } else {

            // have to add the product
            let productObj = {
                productId: pId,
                productTitle: pTitle,
                productPrice: pPrice,
                productQuantity: 1
            }

            productCart.push(productObj);
            localStorage.setItem("cart", JSON.stringify(productCart));

            console.log("Product is added.");

        }

    }

}