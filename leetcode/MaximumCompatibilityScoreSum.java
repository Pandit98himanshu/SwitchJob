package leetcode;

import java.util.*;

/**
 * Created at : 02/08/21
 * <p>
 * <a href=https://leetcode.com/problems/maximum-compatibility-score-sum/>5825. Maximum Compatibility Score Sum</a>
 *
 * @author Himanshu Shekhar
 */

public class MaximumCompatibilityScoreSum {
    static class Solution {
        /**
         * Check how many answers are matching from current student with current mentor
         *
         * @param studentAnswers answers given by current student
         * @param mentorAnswers  answers given by current mentor
         * @return total answers matched
         */
        private int answersMatched(int[] studentAnswers, int[] mentorAnswers) {
            int numberOfQuestions = studentAnswers.length;
            int answersMatched = 0;
            for (int i = 0; i < numberOfQuestions; i++)
                if (studentAnswers[i] == mentorAnswers[i])
                    answersMatched++;

            return answersMatched;
        }

        private int maxCompatibilityScoreSum = 0;

        /**
         * Making all combinations of student and mentor and check for
         * best pairs which gives maximum compatibility score sum
         *
         * @param students      students answers
         * @param mentors       mentors answers
         * @param mentorsMapped mentors will have one student assigned
         * @param score         compatibility score
         */
        public void backtrack(int[][] students, int[][] mentors, List<Integer> mentorsMapped, int score) {
            int totalMentorsMapped = mentorsMapped.size();
            // if all mentors have been assigned (whence all students must be assigned a mentor)
            // then store maximum compatibility score sum
            if (totalMentorsMapped == mentors.length) {
                maxCompatibilityScoreSum = Math.max(maxCompatibilityScoreSum, score);
                return;
            }
            // for each student check for all mentors (for maximum compatibility score sum)
            for (int i = 0; i < mentors.length; i++) {
                // if mentor "i" is already assigned move to next
                if (mentorsMapped.contains(i))
                    continue;
                // mentor "i" will be assigned a student
                mentorsMapped.add(i);
                int last = mentorsMapped.size() - 1;
                // compatibility score if current student is mapped to mentor[i]
                int currentScore = answersMatched(students[last], mentors[i]);
                System.out.println("student = " + last + " mentor = " + i);
                backtrack(students, mentors, mentorsMapped, score + currentScore);
                // remove the current mentor so that he can be assigned another student
                mentorsMapped.remove(last);
            }
            System.out.println();
        }

        /**
         * <strong>Brute Force</strong>
         * <p>Copied from <a href=https://leetcode.com/problems/maximum-compatibility-score-sum/discuss/1360805/Backtracking-with-STL-or-10-lines-of-code-or-C++/1024450>leetcode discuss comment</a>
         *
         * @param students answers given by student
         * @param mentors  answers given by mentor
         * @return maximum compatibility sum
         */
        public int maxCompatibilitySum(int[][] students, int[][] mentors) {
            backtrack(students, mentors, new ArrayList<>(), 0);
            return maxCompatibilityScoreSum;
        }

        /**
         * <strong>Brute Force</strong>
         * <p>Better way to write solution than {@link leetcode.MaximumCompatibilityScoreSum.Solution#maxCompatibilitySum(int[][], int[][])}
         */
        public int maxCompatibilitySum2(int[][] students, int[][] mentors) {
            return makeCombinations(students, mentors, 0, new boolean[mentors.length]);
        }

        /**
         * <strong>Brute Force</strong>
         *
         * @param students answers given by student
         * @param mentors  answers given by mentor
         * @param i        searching mentor for i<sup>th</sup> student
         * @return maximum compatibility sum
         */
        private int makeCombinations(int[][] students, int[][] mentors, int i, boolean[] mentorAssigned) {
            // we assigned all students a mentor
            if (i == students.length)
                return 0;

            int score = 0;          // stores max compatibility score sum

            for (int j = 0; j < mentors.length; j++) {
                // skip already assigned mentor
                if (mentorAssigned[j])
                    continue;
                // mark the mentor assigned
                mentorAssigned[j] = true;
                score = Math.max(score,
                        answersMatched(students[i], mentors[j]) + makeCombinations(students, mentors, i + 1, mentorAssigned));
                // remove the current mentor so that he can be assigned another student
                mentorAssigned[j] = false;
            }
            return score;
        }

        /**
         * <strong>NOT WORKING</strong>
         */
        public int maxCompatibilitySum1(int[][] students, int[][] mentors) {
            int numberOfQuestions = students[0].length;
            int compatibilityScore = 0;
            HashMap<Integer, Integer> map = new HashMap<>();

            // first assign those students whose answers exactly matches with a mentor
            for (int i = 0; i < students.length; i++)
                for (int j = 0; j < mentors.length; j++)
                    if (Arrays.equals(students[i], mentors[j]))
                        if (!map.containsValue(j)) {
                            map.put(i, j);
                            compatibilityScore += numberOfQuestions;
                        }

            // sequentially assign mentor to students
            for (int i = 0; i < students.length; i++) {
                // if a student have been assigned a mentor
                if (map.containsKey(i))
                    continue;

                int maxAnswersMatched = 0, bestMatchIndex = 0;
                for (int j = 0; j < mentors.length; j++) {
                    // if a mentor have already been assigned
                    if (map.containsValue(j))
                        continue;

                    int answersMatched = answersMatched(students[i], mentors[j]);
                    if (maxAnswersMatched < answersMatched) {
                        maxAnswersMatched = answersMatched;
                        bestMatchIndex = j;
                    }
                }
                map.put(i, bestMatchIndex);
                compatibilityScore += maxAnswersMatched;

            }

            return compatibilityScore;
        }
    }

    public static void main(String[] args) {
        int[][] students = {{1, 1, 1}, {0, 0, 1}, {0, 0, 1}, {0, 1, 0}};
        int[][] mentors = {{1, 0, 1}, {0, 1, 1}, {0, 1, 0}, {1, 1, 0}};

        System.out.println(new Solution().maxCompatibilitySum2(students, mentors));
    }
}
