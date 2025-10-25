import random

def treasure_hunt():
    print("🏝️ Welcome to the Treasure Hunt Adventure!")
    print("You are trapped on a mysterious island.")
    print("Find the hidden treasure using clues before your chances run out!\n")

    # grid 5x5
    grid_size = 5
    treasure_x = random.randint(1, grid_size)
    treasure_y = random.randint(1, grid_size)

    chances = 7
    last_distance = None

    while chances > 0:
        try:
            print(f"📍 Chances left: {chances}")
            x = int(input("Enter X coordinate (1–5): "))
            y = int(input("Enter Y coordinate (1–5): "))

            if x < 1 or x > 5 or y < 1 or y > 5:
                print("⚠️ Coordinates must be between 1 and 5!\n")
                continue

            distance = abs(treasure_x - x) + abs(treasure_y - y)

            if distance == 0:
                print("\n💎 CONGRATULATIONS! You found the treasure! 🏆")
                print("You escaped the island with riches beyond imagination!")
                break

            if last_distance is None:
                print("🧭 You start your journey... keep going!")
            else:
                if distance < last_distance:
                    print("🔥 You’re getting closer!")
                elif distance > last_distance:
                    print("❄️ You’re moving away!")
                else:
                    print("😐 You’re at the same distance as before!")

            last_distance = distance
            chances -= 1
            print()
        except ValueError:
            print("❌ Please enter numbers only!\n")

    else:
        print("\n💀 You ran out of chances!")
        print(f"The treasure was hidden at ({treasure_x}, {treasure_y}). Better luck next time!")

# Run the game
treasure_hunt()
