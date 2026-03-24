#!/usr/bin/env bash
set -euo pipefail

BASE_URL="${BASE_URL:-http://localhost:8080}"

echo "1) Register manager"
curl -s -X POST "$BASE_URL/api/auth/register" \
  -H "Content-Type: application/json" \
  -d '{"username":"manager_auto","email":"manager_auto@mail.com","password":"123456","role":"MANAGER"}' | jq .

echo "2) Login manager"
TOKEN=$(curl -s -X POST "$BASE_URL/api/auth/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"manager_auto","password":"123456"}' | jq -r '.data.token')

echo "3) Create project"
curl -s -X POST "$BASE_URL/api/projects" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Project Auto Demo","description":"Created by script"}' | jq .
