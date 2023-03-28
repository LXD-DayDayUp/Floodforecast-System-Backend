package com.example.RK;

//龙格库塔法，报告第五部分70页
public class FloodControl {
    public static void main(String[] args) {
        double max_volume = 1000; // 最大蓄水量为1000
        double init_level = 500; // 初始水位为500
        double t = 0; // 初始时间为0
        double delta_t = 0.1; // 时间步长为0.1小时
        int n_steps = 100; // 模拟100个时间步长

        // 初始化水位和流量
        double h = init_level;
        double q_in = inflow(t);
        double q_out = outflow(t, h);

        // 依次模拟每个时间步长
        for (int i = 0; i < n_steps; i++) {
            // 使用RK4方法计算下一个时间步长的水位
            double k1 = (q_in - q_out) / max_volume;
            double k2 = (inflow(t + delta_t / 2) - outflow(t + delta_t / 2, h + k1 * delta_t / 2)) / max_volume;
            double k3 = (inflow(t + delta_t / 2) - outflow(t + delta_t / 2, h + k2 * delta_t / 2)) / max_volume;
            double k4 = (inflow(t + delta_t) - outflow(t + delta_t, h + k3 * delta_t)) / max_volume;
            h += (k1 + 2 * k2 + 2 * k3 + k4) * delta_t / 6;

            // 更新流入量和出流量
            t += delta_t;
            q_in = inflow(t);
            q_out = outflow(t, h);

            // 输出当前时间、水位和流量
            System.out.printf("t = %.1f, h = %.1f, q_in = %.1f, q_out = %.1f\n", t, h, q_in, q_out);
        }
    }

    // 计算流入量，t表示时间（小时）
    public static double inflow(double t) {
        return 10 * Math.sin(t);
    }

    // 计算出流量，t表示时间（小时），h表示当前水位（米）
    public static double outflow(double t, double h) {
        double max_outflow = 20; // 最大出流量为20
        double overflow_threshold = 800; // 水位超过800时开始溢流
        if (h > overflow_threshold) {
            return max_outflow + 0.1 * (h - overflow_threshold);
        } else {
            return max_outflow;
        }
    }
}
