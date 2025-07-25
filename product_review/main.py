# main.py

from etl.extract import extract_reviews
from etl.transform import transform_reviews
from etl.load import load_to_mongodb
from config.settings import PAGES

def run_etl():
    for page in range(1, PAGES + 1):
        raw = extract_reviews(page)
        transformed = transform_reviews(raw)
        load_to_mongodb(transformed)
        print(f"✅ Page {page} processed.\n")

if __name__ == "__main__":
    import schedule
    import time

    print("📅 ETL started.")
    run_etl()

    # Optional scheduler
    # schedule.every(10).hours.do(run_etl)
    # while True:
    #     schedule.run_pending()
    #     time.sleep(1)
