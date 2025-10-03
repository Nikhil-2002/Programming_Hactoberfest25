import requests

def ip_geolocate(ip="8.8.8.8"):
    url = f"https://ipapi.co/{ip}/json/"
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        print(f"IP: {data['ip']}, City: {data['city']}, Country: {data['country_name']}")
    else:
        print("Failed to fetch IP info")


