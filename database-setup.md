# Database Setup Instructions

## MongoDB Setup

### 1. Install MongoDB
- Download MongoDB Community Server from https://www.mongodb.com/try/download/community
- Install MongoDB on your system
- Start MongoDB service

### 2. Create Database
The application will automatically create the database `disaster_relief_db` when it starts.

### 3. Verify Connection
You can verify the connection by checking if the database is created:
```bash
# Connect to MongoDB
mongo

# List databases
show dbs

# Use the disaster relief database
use disaster_relief_db

# List collections
show collections
```

### 4. Sample Data (Optional)
You can add sample data by registering volunteers and creating donations through the web interface.

## Collections

The application will create the following collections:
- `volunteers` - Volunteer information
- `donations` - Donation records
- `relief_camps` - Relief camp information

## Troubleshooting

### MongoDB Connection Issues
1. Ensure MongoDB is running on port 27017
2. Check if the connection string in application.properties is correct
3. Verify MongoDB service is started

### Database Access Issues
1. Check MongoDB logs for errors
2. Ensure proper permissions for database access
3. Verify network connectivity
