## ⚙️ Features

- ✅ Real-time Amazon review extraction
- 🔧 Data transformation and cleaning
- 🧩 Modular codebase for maintainability
- 💾 MongoDB integration
- 🕓 Optional 10-hour schedule via `schedule`

---

## 🚀 Getting Started

### 1. Clone the repo
git clone https://github.com/your-username/product_review_etl.git

cd product_review_etl

2. Install dependencies
pip install -r requirements.txt

3. Set your API Key
Edit config/settings.py:

RAPIDAPI_KEY = "your_rapidapi_key_here"
You can get your API key from RapidAPI - Real-Time Amazon Data API



🛠 Configuration
In config/settings.py, configure the following:

ASIN = "B0CMZFCQ6D"          # Product ASIN to extract reviews for
PAGES = 3                    # Number of pages to extract
MONGO_URI = "mongodb://localhost:27017/"
DB_NAME = "amazon"
COLLECTION_NAME = "reviews"
🏃 Running the ETL
Manual Execution:
bash
Copy
Edit
python main.py

<img width="1524" height="645" alt="image" src="https://github.com/user-attachments/assets/67817952-9a87-41c1-85d0-b79e9723d124" />
Mongodb compass
