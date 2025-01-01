use restaurent;
-- Creating Orders table
CREATE TABLE Orders1 (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) DEFAULT 0.00
);
-- Creating Order_Details table
CREATE TABLE Order_Details (
    detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    dish_name VARCHAR(50),
    quantity INT,
    amount DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE
);
select*from orders1
select * from Order_Details
select o.order_id,o.order_date,o.total_amount,od.dish_name,od.quantity,od.amount from orders1 o join order_details od on o.order_id=od.order_id
select sum(total_amount) from orders1 where month(order_date)=11;
