import heapq

def solution(book_time):
    answer = 0
    room = []
    book_time = sorted(book_time, key = lambda x : x[0])
    for s, e in book_time :
        start_time = int(s[:2]) * 60 + int(s[3:])
        end_time = int(e[:2]) * 60 + int(e[3:]) + 10
        heapq.heappush(room, end_time)
        while room and room[0] <= start_time :
            heapq.heappop(room)
        answer = max(answer, len(room))
    return answer  # 최소 객실의 수