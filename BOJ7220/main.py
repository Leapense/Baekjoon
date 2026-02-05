def solve():
    N, M = map(int, input().split())
    p = list(map(int, input().split()))
    rooms = []
    for _ in range(N):
        rooms.append(list(map(int, input().split())))
        
    eliminated = set()
    witnesses = set()
    for turn in range(N):
        target = p[turn]
        target_room = rooms[turn][target - 1]
        in_room = []
        for player in range(1, N + 1):
            if player not in eliminated and rooms[turn][player - 1] == target_room:
                in_room.append(player)
                
        eliminated.add(target)
        
        for player in in_room:
            if player != target:
                witnesses.add(player)
                
        remaining_players = [player for player in range(1, N + 1) if player not in eliminated]
        red_votes = sum(1 for player in remaining_players if player in witnesses)
        yellow_votes = len(remaining_players) - red_votes + 1
        
        if red_votes > yellow_votes:
            print(turn + 1)
            return
    print(N)
    
solve()