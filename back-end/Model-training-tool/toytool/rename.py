import os

# 遍历文件夹下所有jpg文件
for filename in os.listdir('.'):
    if filename.endswith('.jpg') or filename.endswith('.JPG'):
        # 将文件名中的 苹果黑星病 替换为 Apple_Black_Rot_Disease
        new_filename = filename.replace('Grape_Black_Rot_Disease ', 'Grape_Black_Rot_Disease')
        # 重命名文件
        os.rename(filename, new_filename)
