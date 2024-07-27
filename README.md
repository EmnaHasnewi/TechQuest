# TechQuest
This project is an IT equipment request management system designed to streamline the process of submitting, tracking, and managing equipment requests within an organization. Employees can submit requests for IT equipment, track the status of their requests, and IT managers can review and approve or reject these requests.
## Features

### Employee Features:
- **Authentication**: Secure login for employees.
- **Submit Requests**: Employees can submit requests specifying the type of equipment needed and the reason for the request.
- **Track Requests**: Employees can view the status of their submitted requests in a table format with state information.

### IT Manager Features:
- **Authentication**: Secure login for IT managers.
- **Review Requests**: IT managers can view and evaluate submitted requests.
- **Approve/Reject Requests**: IT managers can approve or reject requests based on company policies and equipment availability.
- **Email Notifications**: Automated email notifications to employees regarding the approval or rejection of their requests.

## Technology Stack

- **Frontend**: Angular
- **Backend**: Spring Boot
- **Database**: MySQL

## Project Structure

- **Frontend**: Developed in Angular, including components for request submission, management, and status tracking.
- **Backend**: Spring Boot-based RESTful APIs handling business logic and data processing.
- **Database**: MySQL database for storing user information, requests, and request statuses.

## Installation

### Prerequisites
- Node.js and npm installed
- Angular CLI installed
- Java Development Kit (JDK) installed
- MySQL server installed and running

### Frontend Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/it-equipment-request-management.git
    cd it-equipment-request-management/frontend
    ```

2. Install dependencies:
    ```bash
    npm install
    ```

3. Start the Angular development server:
    ```bash
    ng serve
    ```

### Backend Setup

1. Navigate to the backend directory:
    ```bash
    cd ../backend
    ```

2. Configure the `application.yml` file with your MySQL database credentials.

3. Build and run the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```

### Database Setup

1. Create a MySQL database:
    ```sql
    CREATE DATABASE it_request_management;
    ```

2. Update the `application.yml` file with your database configuration.

## Usage

1. Open your browser and navigate to `http://localhost:4200`.
2. Login as an employee or IT manager to access the respective features.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.
