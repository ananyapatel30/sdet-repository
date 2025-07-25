# config/settings.py

RAPIDAPI_HOST = "real-time-amazon-data.p.rapidapi.com"
RAPIDAPI_KEY = ""  # Replace with your actual key
ASIN = "B0CMZFCQ6D"
PAGES = 3

HEADERS = {
    "X-RapidAPI-Key": RAPIDAPI_KEY,
    "X-RapidAPI-Host": RAPIDAPI_HOST,
}

MONGO_URI = "mongodb://localhost:27017/"
DB_NAME = "amazon"
COLLECTION_NAME = "reviews"
