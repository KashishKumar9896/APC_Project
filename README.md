# Disaster Relief Management System

A comprehensive Spring Boot application for managing disaster relief operations, including volunteer coordination, donation tracking, and relief camp management.

## Features

### Core Functionality
- **Volunteer Management**: Register, manage, and coordinate volunteers with skills tracking
- **Donation Tracking**: Track donations from registration to distribution with real-time status updates
- **Relief Camp Management**: Manage relief camps, track capacity, and coordinate resources
- **JWT Authentication**: Secure login system for volunteers and administrators
- **Role-based Access Control**: Different permissions for volunteers and admins

### Technical Features
- **Spring Boot 3.5.5** with Java 17
- **MongoDB** for data persistence
- **JWT** for authentication and authorization
- **Thymeleaf** for server-side rendering
- **Bootstrap 5** for responsive frontend
- **RESTful API** design
- **Hibernate lifecycle** for resource allocation

## Project Structure

```
src/
├── main/
│   ├── java/com/example/disasterrelief/
│   │   ├── config/
│   │   │   └── WebSecurityConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── DonationController.java
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── ReliefCampController.java
│   │   │   ├── VolunteerController.java
│   │   │   └── WebController.java
│   │   ├── model/
│   │   │   ├── Donation.java
│   │   │   ├── ReliefCamp.java
│   │   │   └── Volunteer.java
│   │   ├── repository/
│   │   │   ├── DonationRepository.java
│   │   │   ├── ReliefCampRepository.java
│   │   │   └── VolunteerRepository.java
│   │   ├── security/
│   │   │   ├── AuthTokenFilter.java
│   │   │   ├── UserDetailsServiceImpl.java
│   │   │   └── UserPrincipal.java
│   │   ├── service/
│   │   │   ├── DonationService.java
│   │   │   ├── ReliefCampService.java
│   │   │   └── VolunteerService.java
│   │   ├── utils/
│   │   │   └── JwtUtils.java
│   │   └── DisasterReliefApplication.java
│   └── resources/
│       ├── application.properties
│       └── templates/
│           ├── index.html
│           ├── login.html
│           ├── signup.html
│           └── dashboard.html
└── test/
    └── java/com/example/disasterrelief/
        └── DisasterReliefApplicationTests.java
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MongoDB 4.4 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd disaster-relief-management
```

### 2. Configure MongoDB
Make sure MongoDB is running on your local machine:
```bash
# Start MongoDB service
mongod
```

### 3. Update Configuration
Edit `src/main/resources/application.properties` if needed:
```properties
# MongoDB Configuration
spring.data.mongodb.uri=mongodb://localhost:27017/disaster_relief_db
spring.data.mongodb.database=disaster_relief_db

# JWT Configuration
jwt.secret=mySecretKey123
jwt.expiration=86400

# Server Configuration
server.port=8080
```

### 4. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/auth/signin` - User login
- `POST /api/auth/signup` - User registration

### Volunteers
- `GET /api/volunteers` - Get all volunteers (Admin only)
- `GET /api/volunteers/{id}` - Get volunteer by ID
- `POST /api/volunteers/register` - Register new volunteer
- `PUT /api/volunteers/{id}` - Update volunteer
- `DELETE /api/volunteers/{id}` - Delete volunteer (Admin only)
- `GET /api/volunteers/available` - Get available volunteers
- `GET /api/volunteers/city/{city}` - Get volunteers by city
- `GET /api/volunteers/state/{state}` - Get volunteers by state
- `GET /api/volunteers/skill/{skill}` - Get volunteers by skill

### Donations
- `GET /api/donations` - Get all donations (Admin only)
- `GET /api/donations/{id}` - Get donation by ID
- `POST /api/donations/create` - Create new donation
- `PUT /api/donations/{id}` - Update donation
- `DELETE /api/donations/{id}` - Delete donation (Admin only)
- `GET /api/donations/status/{status}` - Get donations by status
- `GET /api/donations/type/{type}` - Get donations by type
- `GET /api/donations/relief-camp/{reliefCampId}` - Get donations by relief camp
- `GET /api/donations/donor/{donorEmail}` - Get donations by donor
- `PUT /api/donations/{id}/approve` - Approve donation (Admin only)
- `PUT /api/donations/{id}/reject` - Reject donation (Admin only)
- `PUT /api/donations/{id}/distribute` - Mark donation as distributed

### Relief Camps
- `GET /api/relief-camps` - Get all relief camps
- `GET /api/relief-camps/public` - Get public relief camps
- `GET /api/relief-camps/{id}` - Get relief camp by ID
- `POST /api/relief-camps` - Create relief camp (Admin only)
- `PUT /api/relief-camps/{id}` - Update relief camp (Admin only)
- `DELETE /api/relief-camps/{id}` - Delete relief camp (Admin only)
- `GET /api/relief-camps/status/{status}` - Get relief camps by status
- `GET /api/relief-camps/city/{city}` - Get relief camps by city
- `GET /api/relief-camps/state/{state}` - Get relief camps by state
- `GET /api/relief-camps/available` - Get available relief camps
- `POST /api/relief-camps/{campId}/volunteers/{volunteerId}` - Add volunteer to camp
- `DELETE /api/relief-camps/{campId}/volunteers/{volunteerId}` - Remove volunteer from camp
- `POST /api/relief-camps/{campId}/donations/{donationId}` - Add donation to camp
- `DELETE /api/relief-camps/{campId}/donations/{donationId}` - Remove donation from camp

## Frontend Pages

### Public Pages
- `/` - Homepage with system overview
- `/login` - User login page
- `/signup` - Volunteer registration page

### Protected Pages
- `/dashboard` - Main dashboard (requires authentication)

## Data Models

### Volunteer
- Personal information (name, email, phone, address)
- Skills and availability status
- Emergency contact information
- Registration timestamps

### Donation
- Donor information
- Donation type and amount
- Status tracking (Pending, Approved, Rejected, Distributed)
- Relief camp assignment
- Approval workflow

### Relief Camp
- Camp details (name, location, capacity)
- Manager information
- Current occupancy tracking
- Associated volunteers and donations
- Facilities and status

## Security Features

- JWT-based authentication
- Role-based access control (Volunteer/Admin)
- Password encryption using BCrypt
- CORS configuration for cross-origin requests
- Input validation and sanitization

## Usage Examples

### Register as a Volunteer
1. Visit `/signup`
2. Fill in personal information and skills
3. Submit registration form
4. Login with credentials

### Create a Donation
1. Visit `/api/donations/create` endpoint
2. Provide donor information and donation details
3. Donation will be created with "PENDING" status

### Manage Relief Camps
1. Login as admin
2. Access dashboard
3. Navigate to Relief Camps section
4. Add/edit/delete camps as needed

## Development

### Running Tests
```bash
mvn test
```

### Building for Production
```bash
mvn clean package -Pproduction
```

### Database Migration
The application uses MongoDB with automatic schema creation. No manual migration is required.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please contact the development team or create an issue in the repository.

## Future Enhancements

- Real-time notifications
- Mobile application
- Advanced reporting and analytics
- Integration with external disaster management systems
- Multi-language support
- Advanced search and filtering capabilities