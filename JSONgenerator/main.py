import json
import random
import string
from datetime import datetime

def random_string(length=5):
    return ''.join(random.choices(string.ascii_lowercase, k=length))

def generate_simple_list(length=10, value_type="int"):
    if value_type == "int":
        return [random.randint(0, 20) for i in range(length)]
    elif value_type == "float":
        return [round(random.uniform(0, 20), 2) for i in range(length)]
    elif value_type == "str":
        return [random_string(random.randint(3, 8)) for i in range(length)]
    return []

def generate_object_list(length=10):
    lst = []
    for i in range(length):
        obj = {
            "name": random_string(random.randint(3, 8)),
            "age": random.randint(18, 80),
            "score": round(random.uniform(0, 20), 2)
        }
        lst.append(obj)
    return lst

def generate_json(simple=True, length=10, value_type="int", algorithm=1, ascending=True, property_name=None, iterations=None):
    data = {
        "list": generate_simple_list(length, value_type) if simple else generate_object_list(length),
        "ascending": ascending,
        "algorithm": algorithm,
        "iterations": 10000,
        "autoChoose": False,
        "property": property_name if not simple else None
    }
    return data

if __name__ == "__main__":
    simple = False # 1 - lista jednowymiarowa / 0 - obiekty
    length = 10
    algorithm = 2
    ascending = True
    property_name = "score"
    val_type = "float"
    iterations=10000

    json_data = generate_json(simple=simple,
                              length=length,
                              value_type=val_type,
                              algorithm=algorithm,
                              ascending=ascending,
                              property_name=property_name,
                              iterations)

    now = datetime.now()
    # filename = now.strftime("%Y%m%d_%H%M%S") + ".json"
    filename = now.strftime("input") + ".json"

    with open(filename, "w") as f:
        json.dump(json_data, f, indent=4)

    print(f"JSON zapisany do pliku: {filename}")
