from PIL import Image
from urllib.parse import urlencode
from urllib.request import urlopen

prompt = input("프롬프트를 입력해주세요: ")

BASE_URI = "http://15.164.220.238:8080/karlo/infer"
params = {
    "key": "29af196d3bb3b46177f3ec5cbe4c44d8",
    "prompt": prompt,
}

image_url = BASE_URI + "?" + urlencode(params)

result = Image.open(urlopen(image_url))
result.show()
