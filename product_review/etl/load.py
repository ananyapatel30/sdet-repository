# etl/load.py

import pymongo
from config.settings import MONGO_URI, DB_NAME, COLLECTION_NAME

def load_to_mongodb(data):
    print("🗃️ Loading into MongoDB...")
    if not data:
        print("⚠️ No reviews to insert.")
        return

    client = pymongo.MongoClient(MONGO_URI)
    db = client[DB_NAME]
    collection = db[COLLECTION_NAME]

    result = collection.insert_many(data)
    print(f"✅ Inserted {len(result.inserted_ids)} documents.")
    client.close()
