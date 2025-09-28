# Troubleshooting Guide

## Common Issues and Solutions

### 1. MongoDB Connection Issues

**Error:** `MongoSocketOpenException` or `Connection refused`

**Solution:**
- Ensure MongoDB is running: `mongod` or start MongoDB service
- Check if MongoDB is running on port 27017: `netstat -an | findstr 27017`
- Verify connection string in `application.properties`

### 2. JWT Token Issues

**Error:** `Invalid JWT token` or `JWT signature does not match`

**Solution:**
- The JWT secret is now properly configured (32+ characters)
- Clear browser cache and cookies
- Check if token is being sent correctly in Authorization header

### 3. Validation Errors

**Error:** `javax.validation` not found

**Solution:**
- Fixed: Updated all validation imports to use `jakarta.validation`
- This is compatible with Spring Boot 3.x

### 4. Port Already in Use

**Error:** `Port 8080 was already in use`

**Solution:**
- Change port in `application.properties`: `server.port=8081`
- Or kill the process using port 8080: `netstat -ano | findstr :8080`

### 5. CORS Issues

**Error:** `CORS policy` errors in browser

**Solution:**
- CORS is configured to allow all origins
- Check if the frontend is making requests to the correct port
- Verify the API endpoints are accessible

### 6. Authentication Issues

**Error:** `Access Denied` or `Unauthorized`

**Solution:**
- Ensure you're logged in and have a valid JWT token
- Check if the user has the correct role (VOLUNTEER/ADMIN)
- Verify the token is being sent in the Authorization header

### 7. Database Issues

**Error:** `Collection not found` or `Index creation failed`

**Solution:**
- MongoDB will create collections automatically
- Check if MongoDB has proper permissions
- Verify the database name in connection string

## Debugging Steps

### 1. Check Application Logs
Look for error messages in the console output when starting the application.

### 2. Test Health Endpoint
Visit `http://localhost:8080/health` to check if the application is running.

### 3. Test API Endpoints
Use Postman or curl to test API endpoints:
```bash
# Test health
curl http://localhost:8080/health

# Test registration
curl -X POST http://localhost:8080/api/volunteers/register \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john@example.com","password":"password123","phoneNumber":"1234567890","address":"123 Main St","city":"City","state":"State","zipCode":"12345"}'
```

### 4. Check MongoDB
Connect to MongoDB and verify the database:
```bash
mongo
use disaster_relief_db
show collections
```

## Common Solutions

### Restart Application
1. Stop the application (Ctrl+C)
2. Clean and rebuild: `mvn clean compile`
3. Start again: `mvn spring-boot:run`

### Clear Browser Data
1. Clear cookies and local storage
2. Hard refresh the page (Ctrl+F5)
3. Try in incognito/private mode

### Check Dependencies
Ensure all Maven dependencies are downloaded:
```bash
mvn dependency:resolve
```

## Still Having Issues?

1. Check the console output for specific error messages
2. Verify MongoDB is running and accessible
3. Ensure Java 17+ is installed and configured
4. Check if all required ports are available
5. Review the application.properties configuration

## Contact Support

If you continue to experience issues, please provide:
- Error messages from console
- Steps to reproduce the issue
- Your system configuration (OS, Java version, MongoDB version)
- Any custom modifications made to the code
