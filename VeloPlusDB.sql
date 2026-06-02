DROP DATABASE IF EXISTS veloplus_db;
CREATE DATABASE veloplus_db;
USE veloplus_db;

CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(100)
);

CREATE TABLE size (
    size_id INT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE status (
    status_id INT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE client_kind (
    kind_id INT AUTO_INCREMENT PRIMARY KEY,
    label VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE supplier (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(50) NOT NULL,
    phone VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) UNIQUE,
    address VARCHAR(100)
);

CREATE TABLE color (
    color_id INT AUTO_INCREMENT PRIMARY KEY,
    color_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE payment_method (
    payment_method_id INT AUTO_INCREMENT PRIMARY KEY,
    payment_method_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE client (
    client_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NULL,
    phone VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    address VARCHAR(100) NOT NULL,
    kind_id INT NOT NULL,
    category_id INT NOT NULL,

    FOREIGN KEY (category_id) REFERENCES category(category_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (kind_id) REFERENCES client_kind(kind_id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE bike (
    bike_id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    size_id INT NOT NULL,
    color_id INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    is_used BOOLEAN NOT NULL,
    condition_note VARCHAR(100) NULL,
    purchase_price DECIMAL(10,2) NULL,
    description VARCHAR(100) NULL,

    FOREIGN KEY (color_id) REFERENCES color(color_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (size_id) REFERENCES size(size_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT chk_bike_unit_price CHECK (unit_price >= 0),
    CONSTRAINT chk_bike_purchase_price CHECK (purchase_price IS NULL OR purchase_price >= 0),
    CONSTRAINT chk_bike_used_fields CHECK ( (is_used = FALSE AND condition_note IS NULL AND purchase_price IS NULL) OR (is_used = TRUE))
);

CREATE TABLE part (
    part_id INT AUTO_INCREMENT PRIMARY KEY,
    reference INT NOT NULL UNIQUE,
    part_name VARCHAR(50) NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    stock_quantity INT NOT NULL,
    supplier_id INT NOT NULL,

    CONSTRAINT chk_part_unit_price CHECK (unit_price >= 0),
    CONSTRAINT chk_part_stock_quantity CHECK (stock_quantity >= 0),
    FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE sale_order (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    order_date DATE NOT NULL,
    status_id INT NOT NULL,
    deposit_amount DECIMAL(10,2) NULL,
    is_professional_order BOOLEAN NOT NULL,
    notes VARCHAR(100) NULL,

    CONSTRAINT chk_sale_order_deposit CHECK (deposit_amount IS NULL OR deposit_amount >= 0),
    FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE sale_order_line (
    order_id INT NOT NULL,
    order_line_id INT NOT NULL,
    bike_id INT NULL,
    part_id INT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    discount_percent DECIMAL(5,2) NULL,
    
	PRIMARY KEY (order_id, order_line_id),
    
    CONSTRAINT chk_sale_order_line_quantity CHECK (quantity > 0),
    CONSTRAINT chk_sale_order_line_unit_price CHECK (unit_price >= 0),
    CONSTRAINT chk_sale_order_line_discount CHECK (discount_percent IS NULL OR discount_percent BETWEEN 0 AND 100),
    CONSTRAINT chk_sale_order_line_product CHECK ((bike_id IS NOT NULL AND part_id IS NULL) OR (bike_id IS NULL AND part_id IS NOT NULL)),
    FOREIGN KEY (order_id) REFERENCES sale_order(order_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (bike_id) REFERENCES bike(bike_id),
    FOREIGN KEY (part_id) REFERENCES part(part_id)
);

CREATE TABLE repair_order (
    repair_id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    problem_description VARCHAR(200) NOT NULL,
    deposit_date DATE NOT NULL,
    appointment_date DATE NULL,
    estimate_amount DECIMAL(10,2) NOT NULL,
    accepted_estimate BOOLEAN NOT NULL,
    labor_hours DECIMAL(10,2) NULL,
    status_id INT NOT NULL,
    notes VARCHAR(150) NULL,

    CONSTRAINT chk_repair_order_estimate CHECK (estimate_amount >= 0),
    CONSTRAINT chk_repair_order_labor_hours CHECK (labor_hours IS NULL OR labor_hours >= 0),
    CONSTRAINT chk_repair_order_dates CHECK (appointment_date IS NULL OR appointment_date >= deposit_date),
    FOREIGN KEY (status_id) REFERENCES status(status_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE invoice (
    invoice_id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT NOT NULL,
    order_id INT NULL,
    repair_id INT NULL,
    invoice_date DATE NOT NULL,
    payment_method_id INT NOT NULL,
    payment_date DATE NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    is_paid BOOLEAN NOT NULL,

    FOREIGN KEY (client_id) REFERENCES client(client_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    FOREIGN KEY (order_id) REFERENCES sale_order(order_id),
    FOREIGN KEY (repair_id) REFERENCES repair_order(repair_id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_method(payment_method_id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT chk_invoice_total_amount CHECK (total_amount >= 0),
    CONSTRAINT chk_invoice_payment_date CHECK (payment_date IS NULL OR payment_date >= invoice_date),
    CONSTRAINT chk_invoice_source CHECK ((order_id IS NOT NULL AND repair_id IS NULL) OR (order_id IS NULL AND repair_id IS NOT NULL))
);