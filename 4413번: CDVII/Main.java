import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static class Record {
        String plate;
        int day;
        int hour;
        int minute;
        boolean enter;
        int km;

        Record(String plate, int day, int hour, int minute, boolean enter, int km) {
            this.plate = plate;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.enter = enter;
            this.km = km;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder output = new StringBuilder();
        boolean firstCase = true;

        while (true) {
            // 요금표 줄 읽기 (빈 줄 건너뜀)
            int[] toll = null;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    // 더 이상 처리할 케이스 없음
                    if (output.length() > 0) {
                        System.out.print(output.toString());
                    }
                    return;
                }
                line = line.trim();
                if (!line.isEmpty()) {
                    StringTokenizer st = new StringTokenizer(line);
                    int[] temp = new int[24];
                    int idx = 0;
                    while (st.hasMoreTokens() && idx < 24) {
                        temp[idx++] = Integer.parseInt(st.nextToken());
                    }
                    // 24개가 아니면 잘못된 줄이지만, 문제 조건상 항상 24개라고 가정
                    toll = temp;
                    break;
                }
                // 빈 줄이면 다음 줄로
            }

            // 기록들 읽기: 빈 줄이나 EOF까지
            List<Record> records = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    // 이 케이스 끝
                    break;
                }
                StringTokenizer st = new StringTokenizer(line);
                if (st.countTokens() < 4) {
                    continue; // 안전 방어
                }
                String plate = st.nextToken();
                String timeStr = st.nextToken();   // mm:dd:hh:mm
                String action = st.nextToken();    // enter or exit
                int km = Integer.parseInt(st.nextToken());

                String[] tparts = timeStr.split(":");
                // tparts[0] = month (사용 안 함)
                int day = Integer.parseInt(tparts[1]);
                int hour = Integer.parseInt(tparts[2]);
                int minute = Integer.parseInt(tparts[3]);

                boolean isEnter = "enter".equals(action);
                records.add(new Record(plate, day, hour, minute, isEnter, km));
            }

            // 하나의 케이스 처리
            Map<String, Integer> totalCost = solveCase(records, toll);

            // 출력 정리
            if (!firstCase) {
                output.append('\n'); // 케이스 사이 공백 줄
            }
            firstCase = false;

            List<String> plates = new ArrayList<>(totalCost.keySet());
            Collections.sort(plates);
            for (String plate : plates) {
                int cents = totalCost.get(plate);
                String dollars = String.format(Locale.US, "%.2f", cents / 100.0);
                output.append(plate).append(" $").append(dollars).append('\n');
            }

            // 만약 마지막 케이스가 EOF로 끝날 경우, 위의 while 외부에서 return 처리됨
        }
    }

    private static Map<String, Integer> solveCase(List<Record> records, int[] toll) {
        Map<String, Integer> totalCost = new HashMap<>();

        // (차량 번호, 시간) 기준 정렬
        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record a, Record b) {
                int c = a.plate.compareTo(b.plate);
                if (c != 0) return c;
                if (a.day != b.day) return a.day - b.day;
                if (a.hour != b.hour) return a.hour - b.hour;
                return a.minute - b.minute;
            }
        });

        // 인접한 enter-exit 쌍만 비용 계산
        for (int i = 0; i + 1 < records.size(); i++) {
            Record a = records.get(i);
            Record b = records.get(i + 1);

            if (a.plate.equals(b.plate) && a.enter && !b.enter) {
                int distance = Math.abs(a.km - b.km);
                int rate = toll[a.hour];
                int tripCost = distance * rate + 100; // cent

                // 계정 기본 요금 200 cent는 처음 계산 시에만 추가
                totalCost.putIfAbsent(a.plate, 200);
                totalCost.put(a.plate, totalCost.get(a.plate) + tripCost);
            }
        }

        return totalCost;
    }
}