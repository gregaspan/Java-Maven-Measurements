Below is a simple and clear README.md for the Measurements Jakarta EE Web App:

# Measurements Jakarta EE Web App

This is a Jakarta EE-based web application designed to manage products and their measurements. It allows users to add new products, record measurements, and view data both in table and graph formats. The application also sends email notifications if a measurement is out of the expected tolerance range.

## Features

- **Product Management:** Add, edit, and view products with minimum and maximum allowed temperature values.
- **Measurement Recording:** Save temperature measurements for each product.
- **Data Visualization:** Display measurements in both table and graph views.
- **Email Notification:** Automatically send an email alert when a measurement is outside the acceptable range.
- **Multi-language Support:** Provides English and Slovene language options.

## Technologies Used

- **Jakarta EE:** Enterprise Edition platform for building web applications.
- **JSF (Jakarta Server Faces):** For building the user interface.
- **JPA (Jakarta Persistence API):** For data persistence.
- **Maven:** Build and dependency management.
- **Bootstrap:** Responsive styling and layout.
- **Chart.js:** For rendering measurement graphs.

## Setup and Build

1. **Clone the repository.**
2. **Build the project with Maven:**

   ```bash
   mvn clean install

	3.	Deploy the WAR file to your Jakarta EE application server (e.g., WildFly).

Configuration
	•	Database: The persistence settings are defined in src/main/resources/META-INF/persistence.xml.
	•	Mail: Email notifications are configured to use the java:jboss/mail/MojMail session.
	•	Locales: Language resource bundles can be found in src/main/resources/si/messages_en.properties and src/main/resources/si/messages_sl.properties.

Running the Application
	•	Access the application via the default welcome page:

http://localhost:8080/Measurements/faces/products.xhtml



Enjoy managing your products and measurements!

This README provides a concise overview of the project's purpose, features, technologies used, and basic setup instructions.
