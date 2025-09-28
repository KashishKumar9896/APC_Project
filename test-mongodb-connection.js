// Simple MongoDB connection test
// Run this with: mongo test-mongodb-connection.js

try {
    // Connect to MongoDB
    db = connect("mongodb://localhost:27017/disaster_relief_db");
    
    print("✅ MongoDB Connection: SUCCESS");
    print("📊 Database: " + db.getName());
    
    // List collections
    var collections = db.getCollectionNames();
    print("📁 Collections: " + collections);
    
    // Test write operation
    db.test.insertOne({test: "connection", timestamp: new Date()});
    print("✅ Write Test: SUCCESS");
    
    // Test read operation
    var result = db.test.findOne({test: "connection"});
    print("✅ Read Test: SUCCESS");
    print("📄 Test Document: " + JSON.stringify(result));
    
    // Clean up test document
    db.test.deleteOne({test: "connection"});
    print("✅ Cleanup: SUCCESS");
    
    print("\n🎉 MongoDB is working perfectly!");
    
} catch (error) {
    print("❌ MongoDB Connection: FAILED");
    print("Error: " + error);
}
