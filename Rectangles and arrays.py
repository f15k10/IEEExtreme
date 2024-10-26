#(70/100)
def max_histogram_area(heights):
    max_area = 0
    stack = []
    n = len(heights)

    for i in range(n + 1):
        h = heights[i] if i < n else 0
        while stack and heights[stack[-1]] > h:
            height = heights[stack.pop()]
            width = i if not stack else i - stack[-1] - 1
            max_area = max(max_area, height * width)
        stack.append(i)

    return max_area

def max_area_with_modification(N, X, heights):
    max_area = max_histogram_area(heights)

    for i in range(N):
        if heights[i] < X:
            original_height = heights[i]
            heights[i] = X
            new_area = max_histogram_area(heights)
            max_area = max(max_area, new_area)
            heights[i] = original_height

    return max_area

if __name__ == "__main__":
    N, X = map(int, input().split())
    heights = list(map(int, input().split()))
    result = max_area_with_modification(N, X, heights)
    print(result)
