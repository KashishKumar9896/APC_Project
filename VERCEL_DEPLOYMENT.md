# Vercel Deployment Guide

## Important Note
Java applications on Vercel have specific limitations. Vercel is optimized for Node.js, Python, Go, and other runtimes. For Java deployment, consider alternatives like:
- **Heroku** (still available)
- **Railway.app**
- **Render.com**
- **AWS Elastic Beanstalk**
- **Google Cloud Run**
- **Microsoft Azure App Service**

## Prerequisites
- Vercel account (vercel.com)
- MongoDB Atlas account (already set up)
- Git repository pushed to GitHub

## Setup Instructions

### 1. Add Environment Variables to Vercel
In your Vercel project settings, add the following environment variables:

```
MONGODB_URI = mongodb+srv://Kashishkumar:Krish4321@cluster0.qg5mqif.mongodb.net/disaster_relief_db
MONGODB_DB = disaster_relief_db
JWT_SECRET = (your-super-secret-jwt-key)
JWT_EXPIRATION = 86400
GEOAPIFY_API_KEY = f0613a9b5c73408e8b42fbbe233a523f
SERVER_PORT = 8080
```

### 2. Push to GitHub
```bash
git add .
git commit -m "Prepare for Vercel deployment with environment variables"
git push origin main
```

### 3. Deploy on Vercel
1. Go to vercel.com
2. Click "Add New" → "Project"
3. Select your GitHub repository
4. Click "Import"
5. Under "Environment Variables", add all the variables listed above
6. Click "Deploy"

## Sensitive Files Protected
The following sensitive files are now in `.gitignore` and won't be committed:
- `.env` - Local environment variables
- `.env.local` - Local overrides
- `application-local.properties` - Local configuration
- `application-prod.properties` - Production config

## Local Development
1. Create a `.env.local` file in the project root:
```
MONGODB_URI=mongodb+srv://Kashishkumar:Krish4321@cluster0.qg5mqif.mongodb.net/disaster_relief_db
MONGODB_DB=disaster_relief_db
JWT_SECRET=your-dev-secret-key
JWT_EXPIRATION=86400
GEOAPIFY_API_KEY=f0613a9b5c73408e8b42fbbe233a523f
```

2. Run locally:
```bash
mvn clean spring-boot:run
```

## Recommended: Use Railway or Render Instead
These platforms have better Java support:

### Railway.app:
```bash
# Install Railway CLI
npm i -g @railway/cli

# Login and deploy
railway login
railway init
railway up
```

### Render.com:
- Create account at render.com
- Connect GitHub repository
- Select "New +" → "Web Service"
- Choose Maven as build command: `mvn clean package -DskipTests`
- Set environment variables in dashboard
- Deploy
