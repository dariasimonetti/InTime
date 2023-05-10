const sideMenu = document.querySelector("aside");
const menuBtn = document.querySelector("#menu-btn");
const closeBtn = document.querySelector("#close-btn");
const themeToggler = document.querySelector(".theme-toggler");

// show sidebar
menuBtn.addEventListener('click', () => {
    sideMenu.style.display = 'block';
})

// close sidebar
closeBtn.addEventListener('click', () => {
    sideMenu.style.display = 'none';
})

// change theme
themeToggler.addEventListener('click', () => {
    document.body.classList.toggle('dark-theme-variables');

    themeToggler.querySelector('span:nth-child(1)').classList.toggle('active');
    themeToggler.querySelector('span:nth-child(2)').classList.toggle('active');
})


// fill orders in table 
Orders.forEach(order => {
    const tr = document.createElement('tr');
    const trContent = `
                            <td>${order.productName}</td>
                            <td>${order.productName}</td>
                            <td>${order.productNumber}</td>
                            <td>${order.paymentStatus}</td>
                            <td class="${order.shipping === 'Declined' ? 'danger' : order.shipping === 'pending' ? 'warning' : 'primary'}">${order.shipping}</td>
                            <td class="primary">Details</td>`;

    tr.innerHTML = trContent;
    document.querySelector('table tbody').appendChild(tr);
})

const Orders = [
    {
        productName: 'Foldable Mini Drone',
        productNumber: '87942',
        paymentStatus: 'Due',
        shipping: 'Pending'
    },

    {
        productName: 'LARVENDER GR102 Drone',
        productNumber: '87451',
        paymentStatus: 'Fefunded',
        shipping: 'Delined'
    },

    {
        productName: 'Ruko H21 Pro Drone',
        productNumber: '12748',
        paymentStatus: 'Due',
        shipping: 'Pending'
    },

    {
        productName: 'Drone with Camera Drone',
        productNumber: '88974',
        paymentStatus: 'Paid',
        shipping: 'Delivered'
    },

    {
        productName: 'GPS 8k Drone',
        productNumber: '98748',
        paymentStatus: 'Paid',
        shipping: 'Delivered'
    },

    {
        productName: 'Lozenge Drone',
        productNumber: '00245',
        paymentStatus: 'Paid',
        shipping: 'Delivered'
    },
]