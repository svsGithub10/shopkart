# ShopKart

![ShopKart Logo](com.shopkart/src/main/resources/static/images/logo.png)

ShopKart is an e-commerce platform designed to provide a seamless shopping experience. It includes features such as product listings, user authentication, and order management. The project is currently in development, with plans to implement a payment system and bill generation.

## 🚀 Features
- 🛍️ Product Listing (Not Categorized)
- 🔐 Email OTP-based User Authentication (Signup/Login)
- 🛒 Shopping Cart & Checkout
- 📦 Order Management
- 🌐 RESTful API
- 💳 Upcoming Features: Payment Integration, Bill Generation, Payment Page

## 🛠️ Tech Stack
- **Backend:** Java, Spring Boot
- **Database:** MySQL
- **Authentication:** Spring Security, JWT, Email OTP
- **Build Tool:** Maven
- **Frontend (if applicable):** React.js (if included in the project)

## 📂 Project Structure
```
com.shopkart/
├── src/
│   ├── main/
│   │   ├── java/com/shopkart/   # Java source code
│   │   ├── resources/           # Application properties & static resources
│   ├── test/                    # Unit tests
├── pom.xml                      # Maven build file
├── mvnw, mvnw.cmd                # Maven wrapper scripts
├── README.md                     # Project Documentation
└── HELP.md                       # Additional documentation
```

## 🚀 Installation
1. **Clone the repository:**
   ```sh
   git clone https://github.com/svsGithub10/shopkart.git
   cd shopkart/com.shopkart
   ```
2. **Configure Database:**
   Update `application.properties` or `application.yml` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/shopkart
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```
3. **Build and Run:**
   ```sh
   ./mvnw spring-boot:run
   ```
   
## 🤝 Contributing
Contributions are welcome! Feel free to fork the repo and submit a pull request.

## 📜 License
This project is licensed under the MIT License.

## 🔗 Reference
GitHub Repository: [ShopKart](https://github.com/svsGithub10/shopkart)

## 📧 Contact
For any queries, reach out at [Shantveeresh Sheelavantar](mailto:shantaveereshvs@gmail.com).
