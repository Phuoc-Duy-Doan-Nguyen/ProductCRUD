const apiBaseUrl = 'http://localhost:8080/api/v1/products';

document.addEventListener('DOMContentLoaded', showProducts);

function showProducts() {
    fetch(`${apiBaseUrl}/All`)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('#productTable tbody');
            tbody.innerHTML = '';
            data.forEach(product => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${product.productID}</td>
                    <td>${product.productName}</td>
                    <td>${product.productImg}</td>
                    <td>${product.price}</td>
                    <td>
                        <button onclick="showEditForm(${product.productID})">Edit</button>
                        <button onclick="deleteProduct(${product.productID})">Delete</button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
}

function showAddForm() {
    document.getElementById('productId').value = '';
    document.getElementById('productName').value = '';
    document.getElementById('productImg').value = '';
    document.getElementById('productPrice').value = '';
    document.getElementById('popupTitle').textContent = 'Add Product';
    document.getElementById('overlay').style.display = 'block';
}

function showEditForm(productId) {
    fetch(`${apiBaseUrl}/${productId}`)
        .then(response => response.json())
        .then(product => {
            document.getElementById('productId').value = product.productID;
            document.getElementById('productName').value = product.productName;
            document.getElementById('productImg').value = product.productImg;
            document.getElementById('productPrice').value = product.price;
            document.getElementById('popupTitle').textContent = 'Edit Product';
            document.getElementById('overlay').style.display = 'block';
        })
        .catch(error => console.error('Error fetching product:', error));
}

function hideForm() {
    document.getElementById('overlay').style.display = 'none';
}

function saveOrUpdateProduct() {
    const productId = document.getElementById('productId').value;
    const productName = document.getElementById('productName').value;
    const productImg = document.getElementById('productImg').value;
    const productPrice = document.getElementById('productPrice').value;

    const product = {
        productName: productName,
        productImg: productImg,
        price: parseFloat(productPrice)
    };

    let fetchUrl = `${apiBaseUrl}/add-product`;
    let method = 'POST';
    if (productId) {
        fetchUrl = `${apiBaseUrl}/update/${productId}`;
        method = 'PUT';
    }

    fetch(fetchUrl, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
    .then(response => response.json())
    .then(() => {
        showProducts();
        hideForm();
    })
    .catch(error => console.error('Error saving/updating product:', error));
}

function deleteProduct(productId) {
    fetch(`${apiBaseUrl}/delete/${productId}`, {
        method: 'DELETE'
    })
    .then(() => showProducts())
    .catch(error => console.error('Error deleting product:', error));
}
