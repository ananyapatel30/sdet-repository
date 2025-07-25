# etl/transform.py

from datetime import datetime

def transform_reviews(raw_data):
    print("🔧 Transforming data...")
    if not raw_data or "data" not in raw_data or "reviews" not in raw_data["data"]:
        return []

    item_id = raw_data["data"].get("asin", "")
    reviews = raw_data["data"]["reviews"]

    if reviews:
        print("🔍 Sample Review:")
        print(reviews[0])

    cleaned = []
    for r in reviews:
        cleaned.append({
            "item_id": item_id,
            "review_id": r.get("review_id"),
            "rating": int(r.get("review_star_rating", 0)),
            "title": r.get("review_title"),
            "body": r.get("review_comment"),
            "author": r.get("review_author"),
            "author_id": r.get("review_author_id"),
            "author_url": r.get("review_author_url"),
            "author_avatar": r.get("review_author_avatar"),
            "review_date": r.get("review_date"),
            "verified_purchase": r.get("is_verified_purchase"),
            "helpful_votes": r.get("helpful_vote_statement"),
            "review_images": r.get("review_images"),
            "review_video": r.get("review_video"),
            "review_link": r.get("review_link"),
            "is_vine": r.get("is_vine"),
            "fetched_at": datetime.utcnow()
        })
    return cleaned
