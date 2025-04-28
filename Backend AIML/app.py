from flask import Flask, request, jsonify
import pandas as pd
import os

app = Flask(__name__)

# Load existing datasets
roles_df = pd.read_excel('models/roles_dataset.xlsx')
permissions_df = pd.read_excel('models/permissions_dataset.xlsx')
profiles_df = pd.read_excel('models/profiles_dataset.xlsx')
forums_df = pd.read_excel('models/forums_dataset.xlsx')

# Check if suggestion file exists, else create
suggestions_file = 'models/suggestions_dataset.xlsx'
if os.path.exists(suggestions_file):
    suggestions_df = pd.read_excel(suggestions_file)
else:
    suggestions_df = pd.DataFrame(columns=["suggestion_id", "profile_id", "forum_id", "suggestion_text"])

# API to get all suggestions
@app.route('/suggestions', methods=['GET'])
def get_suggestions():
    return jsonify(suggestions_df.to_dict(orient="records"))

# API to add a new suggestion
@app.route('/suggestions', methods=['POST'])
def add_suggestion():
    data = request.get_json()
    
    if not data or 'profile_id' not in data or 'forum_id' not in data or 'suggestion_text' not in data:
        return jsonify({"error": "Missing fields!"}), 400

    new_id = len(suggestions_df) + 1
    new_suggestion = {
        "suggestion_id": new_id,
        "profile_id": data['profile_id'],
        "forum_id": data['forum_id'],
        "suggestion_text": data['suggestion_text']
    }
    
    global suggestions_df
    suggestions_df = suggestions_df.append(new_suggestion, ignore_index=True)
    
    # Save to Excel
    suggestions_df.to_excel(suggestions_file, index=False)
    
    return jsonify({"message": "Suggestion added successfully!", "suggestion": new_suggestion})

# Basic Home route
@app.route('/')
def home():
    return "<h1>Flask App Running - Suggestions API</h1>"

if __name__ == '_main_':
    app.run(debug=True)