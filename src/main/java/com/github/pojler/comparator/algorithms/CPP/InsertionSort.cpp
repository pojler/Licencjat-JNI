#include <iostream>
#include <chrono>
#include <array>
#include <math.h>

const int N = 1000;

std::array<int, N> sort(std::array<int, N> arr){
    std::array<int, N> result = arr;
    for(int i = 1; i < N; ++i){
        int key = result.at(i);
        int j = i -1;
    

        while(j >= 0 && result.at(j) > key){
            result.at(j+1) = result.at(j);
            j = j-1;
        }
        result.at(j+1) = key;
    }
    return result;
}

int main(){
    std::array<int, N> arr = {81468, 28297, 79628, 45114, 69502, 26580, 83323, 82540, 72513, 15467, 30239, 36874, 17515, 57565, 70660, 10940, 89354, 24933, 47732, 50088, 33181, 75009, 66042, 95527, 5615, 39821, 49312, 34998, 27271, 67203, 49061, 74383, 62509, 60929, 75375, 94589, 34323, 85965, 36288, 36934, 18362, 10408, 64631, 51526, 81405, 49451, 64487, 15354, 49993, 9574, 64393, 17626, 61667, 62996, 85318, 61240, 44010, 61908, 42395, 73103, 17574, 77590, 88766, 17988, 32312, 56470, 46028, 41674, 94897, 92583, 24426, 47586, 41655, 51113, 58250, 84702, 66166, 12805, 44683, 68125, 30980, 61104, 78815, 55543, 50084, 4445, 80936, 13417, 9964, 7139, 36801, 71693, 15947, 54381, 42887, 80394, 37684, 60558, 55933, 10517, 67224, 5215, 10378, 83161, 99130, 27068, 45999, 51240, 59218, 67980, 39849, 29906, 67490, 6561, 51061, 32124, 66023, 7106, 47500, 12544, 31953, 52015, 1304, 95320, 60760, 33404, 71443, 42125, 73691, 86481, 31554, 58561, 14631, 69309, 29990, 86110, 97580, 18355, 3329, 13300, 56015, 56783, 38449, 41756, 34802, 64586, 55952, 89134, 72902, 62492, 12794, 52380, 39065, 19275, 73434, 34366, 56694, 69082, 20161, 95530, 64978, 67262, 31459, 68080, 76475, 88102, 9131, 55850, 35632, 40151, 73232, 55582, 38107, 25353, 71523, 1657, 29788, 16375, 3862, 46304, 94087, 12532, 24598, 70695, 3336, 52550, 53273, 81967, 60190, 61221, 441, 87702, 41613, 61653, 87786, 30003, 78113, 94641, 73144, 69592, 68734, 90337, 27186, 54608, 71233, 25429, 71385, 22351, 63506, 77339, 5390, 38902, 9344, 54314, 52555, 67910, 48697, 46496, 59856, 94011, 9760, 54064, 14184, 56725, 34600, 17672, 92720, 12481, 58091, 54712, 26813, 71805, 95677, 24423, 44993, 31516, 64784, 59548, 11127, 31253, 77701, 81213, 30058, 25530, 33614, 26281, 5322, 78994, 20036, 25420, 52449, 36618, 10471, 49543, 86655, 86999, 94062, 77039, 56123, 26624, 45153, 43115, 71001, 95149, 70440, 69254, 85674, 7212, 8257, 92678, 10554, 90010, 84030, 45670, 59891, 51420, 52113, 8604, 41813, 98032, 8294, 13756, 39101, 96655, 51577, 66832, 74887, 4798, 2281, 41956, 32330, 16776, 85379, 39301, 66543, 81480, 40407, 41097, 94998, 33089, 94854, 18474, 16775, 80053, 35265, 95043, 9977, 82875, 74063, 51394, 37698, 90736, 45606, 15374, 56086, 93504, 74431, 23511, 28354, 82524, 81967, 49091, 7141, 83120, 19174, 37347, 55857, 94278, 70520, 27173, 47103, 82545, 91311, 22139, 631, 5571, 19603, 10781, 19870, 32493, 86395, 11557, 29705, 42936, 63326, 77002, 45123, 4379, 64931, 94693, 27573, 65975, 24288, 3729, 32877, 65302, 99570, 35358, 83683, 5982, 41430, 8834, 14937, 79253, 39063, 57428, 17821, 10748, 8030, 89632, 31322, 89927, 39264, 31807, 66012, 16893, 69253, 3541, 94782, 8265, 5931, 98184, 95515, 86997, 37835, 76377, 3344, 36899, 77873, 8875, 20512, 39708, 53131, 84781, 42039, 88875, 38067, 65681, 45585, 71037, 28124, 88503, 64572, 0, 72202, 4287, 56183, 65112, 27204, 63539, 18898, 61565, 27091, 86909, 12540, 22213, 11680, 89763, 37369, 37215, 14832, 39275, 9591, 62798, 87970, 62892, 61753, 72825, 6437, 96129, 88970, 89080, 17003, 97407, 30243, 99764, 72212, 98577, 75779, 56118, 79295, 51911, 21571, 52254, 82702, 49021, 26554, 94120, 86630, 42581, 94495, 42899, 55855, 40287, 50649, 42015, 12451, 77814, 75502, 18253, 49161, 74251, 7583, 47202, 55516, 69714, 17118, 91006, 84646, 70790, 54724, 24844, 94800, 4806, 55585, 93958, 48423, 97712, 49606, 5535, 52334, 41827, 40128, 89265, 15643, 10211, 53901, 98648, 27032, 55493, 34444, 4943, 42963, 72149, 35897, 16100, 94859, 14956, 25657, 87814, 91708, 35042, 7814, 60457, 43166, 53554, 28086, 9665, 1628, 55547, 17255, 53469, 93460, 73919, 39175, 30966, 95924, 95525, 15009, 66518, 46157, 33170, 83022, 24544, 6344, 11662, 98458, 88210, 88097, 96045, 65105, 32343, 38078, 59799, 31546, 35835, 4387, 94008, 3189, 42785, 28415, 17688, 95107, 8232, 45291, 54154, 24008, 5284, 51816, 22470, 80960, 53026, 76398, 4689, 66546, 3600, 71082, 95992, 85609, 29699, 62107, 74627, 40776, 58850, 99754, 26205, 55069, 79433, 49090, 76972, 94480, 32465, 10018, 50933, 14595, 39113, 81959, 97128, 32818, 74197, 60953, 23256, 81686, 91203, 53110, 42354, 28859, 25875, 8369, 88064, 73529, 96931, 52220, 74557, 16083, 80298, 46593, 53494, 89462, 53468, 7874, 53605, 50548, 72623, 49892, 99181, 67237, 37181, 7616, 68168, 95289, 41654, 76956, 41169, 71955, 77436, 58474, 17589, 54419, 46949, 92854, 36704, 61409, 8767, 32745, 74974, 73332, 53523, 61172, 11229, 78361, 75615, 21386, 42560, 72482, 49963, 43673, 99856, 91184, 85653, 73260, 53760, 40523, 20002, 48091, 3926, 39112, 47172, 46706, 48571, 77519, 71795, 29030, 7830, 97337, 38173, 75587, 57798, 535, 59008, 38609, 20574, 28426, 83381, 13488, 45162, 93002, 9399, 937, 56358, 28179, 89316, 38095, 73165, 35112, 87031, 66915, 68213, 8436, 6781, 24057, 52437, 71021, 86339, 19727, 87210, 56920, 39057, 90909, 74071, 54391, 3287, 70608, 29724, 85885, 65369, 39479, 99987, 97350, 54388, 90235, 20200, 87230, 41244, 61981, 92713, 80845, 43081, 14835, 70679, 14984, 22804, 54109, 35097, 40532, 66346, 28480, 54885, 21632, 10075, 36630, 23680, 61121, 59565, 42354, 1877, 99595, 29680, 48273, 98358, 92621, 18340, 38959, 81698, 86860, 24956, 10753, 92334, 88502, 65033, 79096, 20511, 69490, 27400, 98, 71326, 66317, 7748, 82985, 51650, 26798, 43021, 62411, 88893, 4592, 55885, 51486, 24882, 40286, 86517, 51594, 49261, 27872, 15989, 35381, 64326, 83803, 84847, 3621, 63804, 30339, 74830, 10643, 39239, 94640, 60531, 61576, 6839, 21592, 2894, 31473, 39443, 56273, 41859, 4513, 90663, 50443, 40424, 57995, 27982, 5009, 68024, 61471, 5016, 9269, 93165, 16057, 79269, 78087, 71449, 92207, 35316, 23380, 85721, 90081, 45231, 55640, 20491, 50263, 85231, 41227, 56146, 45582, 26193, 62145, 63932, 81686, 60319, 58531, 51269, 8515, 38983, 6836, 46905, 67289, 79493, 28022, 36438, 36685, 20347, 97210, 84408, 39600, 99370, 69534, 30275, 64738, 95608, 45837, 84491, 14305, 35204, 52360, 98983, 86749, 24110, 50326, 64317, 65210, 27849, 79371, 78529, 16324, 32302, 75664, 52366, 85959, 24961, 58708, 1775, 73217, 79449, 3138, 62536, 86632, 62724, 54359, 74643, 32813, 20269, 35993, 16226, 2562, 80647, 41645, 7392, 28203, 12992, 97300, 6930, 83041, 98905, 15222, 51989, 4643, 35630, 76573, 1972, 54301, 67927, 22592, 73694, 85045, 43219, 70205, 25602, 93235, 79628, 33986, 20622, 80886, 50428, 89690, 76282, 68248, 99021, 2881, 90092, 437, 49082, 75288, 22250, 63723, 59971, 40993, 91996, 75826, 4412, 43278, 28755, 50199, 21826, 10551, 75793, 64806, 93643, 23546, 25991, 3240, 10377, 57616, 47457, 17900, 87896, 9689, 26373, 2188, 8007, 15143, 26486, 59519, 54768, 98113, 91707, 40305, 53117, 79072, 77881, 65960, 98326, 37452, 44194, 57381, 66206, 20836, 48107, 57304, 90821, 2186, 62502, 13427, 8140, 16086, 3971, 14450, 76210, 61912, 45354, 46174, 37142, 16988, 94226, 13902, 76355, 3195, 34841, 15298, 18176, 64565, 40080, 703, 41028, 56452, 94454, 93458, 82412, 20747, 94467, 24209, 80736, 72811, 74107, 59932, 93, 45407, 90689, 21581, 18434, 98171, 48650};
    std::chrono::high_resolution_clock::time_point begin = std::chrono::high_resolution_clock::now();
    std::array<int, N> result = sort(arr);
    std::chrono::high_resolution_clock::time_point end = std::chrono::high_resolution_clock::now();
     for (int i = 0; i < N; i++)
            std::cout<<result.at(i) << " ";
    std::cout << "Elapsed time in miliseconds : "<< std::chrono::duration_cast<std::chrono::milliseconds>(end -begin).count()<< " ms";
    return 0;

}