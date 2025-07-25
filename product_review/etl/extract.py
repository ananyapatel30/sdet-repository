# etl/extract.py

import requests
from config.settings import RAPIDAPI_HOST, HEADERS, ASIN

def extract_reviews(page=1):
    print(f"📥 Extracting page {page}...")
    url = f"https://{RAPIDAPI_HOST}/product-reviews"
    params = {
        "asin": ASIN,
        "page": str(page),
        "country": "US",
        "verified": "false"
    }

    try:
        response = requests.get(url, headers=HEADERS, params=params)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        print("Error fetching data:", response.status_code)
        print(response.json())
        return None
