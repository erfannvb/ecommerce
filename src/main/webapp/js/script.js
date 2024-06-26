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

        showToast("Item added to the cart!");

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

            showToast(existingProduct.productTitle + " quantity increased.")

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

            showToast("Product is added!");

        }

    }

    updateCart();

}

function updateCart() {

    let cartString = localStorage.getItem("cart");
    let cart = JSON.parse(cartString);

    if (cart == null || cart.length === 0) {
        console.log("Cart is empty");
        $(".cart-items").html("( 0 )");
        $(".cart-body").html("<h3>There is no item in your cart.</h3>");
        $(".checkout-btn").attr("disabled", true);
    } else {
        console.log(cart);
        $(".cart-items").html(`( ${cart.length} )`);
        let table = `
            <table class="table table-bordered table-striped table-hover">
                <tr>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Action</th>
                </tr>
        `;

        let totalPrice = 0;

        cart.map((item) => {
            table += `
                <tr>
                    <td>${item.productTitle}</td>
                    <td>${item.productPrice}</td>
                    <td>${item.productQuantity}</td>
                    <td>${item.productQuantity * item.productPrice}</td>
                    <td class="text-center"><button onclick="deleteItemFromCart(${item.productId})"
                     class="btn btn-danger btn-sm">Remove</button></td>
            `;
            totalPrice += item.productQuantity * item.productPrice;
        });
        table += `
                <tr><td colspan="5" style="font-weight: bold; text-align: right">Total Price : ${totalPrice}</td></tr>
            </tr>
        </table>`;
        $(".cart-body").html(table);
        $(".checkout-btn").attr("disabled", false);
    }
}

function deleteItemFromCart(pId) {

    let cart = JSON.parse(localStorage.getItem("cart"));

    let newCart = cart.filter((item) => item.productId !== pId);

    localStorage.setItem("cart", JSON.stringify(newCart));

    updateCart();

    showToast("Item is removed from the cart!");

}

function showToast(content) {
    $("#toast").addClass("display");
    $("#toast").html(content);
    setTimeout(() => {
        $("#toast").removeClass("display");
    }, 2000);
}

function goToCheckout() {
    window.location = "checkout.jsp";
}

$(document).ready(() => {
    updateCart();
})










