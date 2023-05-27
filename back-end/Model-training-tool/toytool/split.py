import os
import random
import shutil

# 功能为按照6-2-2的原则随机划分目录内的图片数据集

# 设置随机种子，保证每次划分结果一致
random.seed(123)

# 定义目录和文件名
data_dir = ".\\"
train_dir = "train"
val_dir = "val"
test_dir = "test"

# 创建目录
os.makedirs(train_dir, exist_ok=True)
os.makedirs(val_dir, exist_ok=True)
os.makedirs(test_dir, exist_ok=True)

# 获取所有图片文件的路径
image_paths = []
for filename in os.listdir(data_dir):
    if filename.endswith(".JPG") or filename.endswith(".png"):
        image_path = os.path.join(data_dir, filename)
        image_paths.append(image_path)

# 随机打乱图片路径
random.shuffle(image_paths)

# 计算划分数量
num_images = len(image_paths)
num_train = int(num_images * 0.6)
num_val = int(num_images * 0.2)
num_test = num_images - num_train - num_val

# 拷贝到训练集
for i in range(num_train):
    filename = os.path.basename(image_paths[i])
    dest_path = os.path.join(train_dir, filename)
    shutil.copy(image_paths[i], dest_path)

# 拷贝到验证集
for i in range(num_train, num_train + num_val):
    filename = os.path.basename(image_paths[i])
    dest_path = os.path.join(val_dir, filename)
    shutil.copy(image_paths[i], dest_path)

# 拷贝到测试集
for i in range(num_train + num_val, num_train + num_val + num_test):
    filename = os.path.basename(image_paths[i])
    dest_path = os.path.join(test_dir, filename)
    shutil.copy(image_paths[i], dest_path)

print(f"划分完成，训练集 {num_train} 张图片，验证集 {num_val} 张图片，测试集 {num_test} 张图片。")
