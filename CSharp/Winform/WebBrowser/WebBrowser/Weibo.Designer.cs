namespace WebBrowser
{
    partial class Weibo
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btn_发布 = new System.Windows.Forms.Button();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // btn_发布
            // 
            this.btn_发布.Location = new System.Drawing.Point(234, 339);
            this.btn_发布.Name = "btn_发布";
            this.btn_发布.Size = new System.Drawing.Size(75, 23);
            this.btn_发布.TabIndex = 0;
            this.btn_发布.Text = "发布";
            this.btn_发布.UseVisualStyleBackColor = true;
            this.btn_发布.Click += new System.EventHandler(this.btn_发布_Click);
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(12, 12);
            this.textBox1.Multiline = true;
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(547, 321);
            this.textBox1.TabIndex = 1;
            // 
            // Weibo
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(573, 371);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.btn_发布);
            this.Name = "Weibo";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Weibo";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btn_发布;
        private System.Windows.Forms.TextBox textBox1;
    }
}