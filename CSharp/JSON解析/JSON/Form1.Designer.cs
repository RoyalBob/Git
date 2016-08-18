namespace JSON
{
    partial class Form1
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.简单JSON = new System.Windows.Forms.Button();
            this.包含对象的JSON = new System.Windows.Forms.Button();
            this.包含对象数组的JSON = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // 简单JSON
            // 
            this.简单JSON.Location = new System.Drawing.Point(17, 22);
            this.简单JSON.Name = "简单JSON";
            this.简单JSON.Size = new System.Drawing.Size(75, 23);
            this.简单JSON.TabIndex = 0;
            this.简单JSON.Text = "简单JSON";
            this.简单JSON.UseVisualStyleBackColor = true;
            this.简单JSON.Click += new System.EventHandler(this.简单JSON_Click);
            // 
            // 包含对象的JSON
            // 
            this.包含对象的JSON.Location = new System.Drawing.Point(114, 22);
            this.包含对象的JSON.Name = "包含对象的JSON";
            this.包含对象的JSON.Size = new System.Drawing.Size(104, 23);
            this.包含对象的JSON.TabIndex = 1;
            this.包含对象的JSON.Text = "包含对象的JSON";
            this.包含对象的JSON.UseVisualStyleBackColor = true;
            this.包含对象的JSON.Click += new System.EventHandler(this.包含对象的JSON_Click);
            // 
            // 包含对象数组的JSON
            // 
            this.包含对象数组的JSON.Location = new System.Drawing.Point(240, 22);
            this.包含对象数组的JSON.Name = "包含对象数组的JSON";
            this.包含对象数组的JSON.Size = new System.Drawing.Size(140, 23);
            this.包含对象数组的JSON.TabIndex = 2;
            this.包含对象数组的JSON.Text = "包含对象数组的JSON";
            this.包含对象数组的JSON.UseVisualStyleBackColor = true;
            this.包含对象数组的JSON.Click += new System.EventHandler(this.包含对象数组的JSON_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(406, 67);
            this.Controls.Add(this.包含对象数组的JSON);
            this.Controls.Add(this.包含对象的JSON);
            this.Controls.Add(this.简单JSON);
            this.Name = "Form1";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button 简单JSON;
        private System.Windows.Forms.Button 包含对象的JSON;
        private System.Windows.Forms.Button 包含对象数组的JSON;
    }
}

