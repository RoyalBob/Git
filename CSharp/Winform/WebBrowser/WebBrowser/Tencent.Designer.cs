namespace WebBrowser
{
    partial class Tencent
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
            this.components = new System.ComponentModel.Container();
            this.panel1 = new System.Windows.Forms.Panel();
            this.btn_发布当前链接到微博 = new System.Windows.Forms.Button();
            this.btn_发布微博 = new System.Windows.Forms.Button();
            this.btn_首页 = new System.Windows.Forms.Button();
            this.btn_停止获取新闻链接 = new System.Windows.Forms.Button();
            this.btn_返回 = new System.Windows.Forms.Button();
            this.btn_获取新闻链接 = new System.Windows.Forms.Button();
            this.listBox1 = new System.Windows.Forms.ListBox();
            this.webBrowser_Tencent = new System.Windows.Forms.WebBrowser();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.btn_发布当前链接到微博);
            this.panel1.Controls.Add(this.btn_发布微博);
            this.panel1.Controls.Add(this.btn_首页);
            this.panel1.Controls.Add(this.btn_停止获取新闻链接);
            this.panel1.Controls.Add(this.btn_返回);
            this.panel1.Controls.Add(this.btn_获取新闻链接);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(866, 23);
            this.panel1.TabIndex = 0;
            // 
            // btn_发布当前链接到微博
            // 
            this.btn_发布当前链接到微博.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_发布当前链接到微博.Location = new System.Drawing.Point(176, 0);
            this.btn_发布当前链接到微博.Name = "btn_发布当前链接到微博";
            this.btn_发布当前链接到微博.Size = new System.Drawing.Size(125, 23);
            this.btn_发布当前链接到微博.TabIndex = 12;
            this.btn_发布当前链接到微博.Text = "发布当前链接到微博";
            this.btn_发布当前链接到微博.UseVisualStyleBackColor = true;
            // 
            // btn_发布微博
            // 
            this.btn_发布微博.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_发布微博.Location = new System.Drawing.Point(107, 0);
            this.btn_发布微博.Name = "btn_发布微博";
            this.btn_发布微博.Size = new System.Drawing.Size(69, 23);
            this.btn_发布微博.TabIndex = 11;
            this.btn_发布微博.Text = "发布微博";
            this.btn_发布微博.UseVisualStyleBackColor = true;
            // 
            // btn_首页
            // 
            this.btn_首页.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_首页.Location = new System.Drawing.Point(51, 0);
            this.btn_首页.Name = "btn_首页";
            this.btn_首页.Size = new System.Drawing.Size(56, 23);
            this.btn_首页.TabIndex = 10;
            this.btn_首页.Text = "首页";
            this.btn_首页.UseVisualStyleBackColor = true;
            // 
            // btn_停止获取新闻链接
            // 
            this.btn_停止获取新闻链接.Dock = System.Windows.Forms.DockStyle.Right;
            this.btn_停止获取新闻链接.Location = new System.Drawing.Point(646, 0);
            this.btn_停止获取新闻链接.Name = "btn_停止获取新闻链接";
            this.btn_停止获取新闻链接.Size = new System.Drawing.Size(110, 23);
            this.btn_停止获取新闻链接.TabIndex = 8;
            this.btn_停止获取新闻链接.Text = "停止获取新闻链接";
            this.btn_停止获取新闻链接.UseVisualStyleBackColor = true;
            // 
            // btn_返回
            // 
            this.btn_返回.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_返回.Location = new System.Drawing.Point(0, 0);
            this.btn_返回.Name = "btn_返回";
            this.btn_返回.Size = new System.Drawing.Size(51, 23);
            this.btn_返回.TabIndex = 9;
            this.btn_返回.Text = "返回";
            this.btn_返回.UseVisualStyleBackColor = true;
            this.btn_返回.Click += new System.EventHandler(this.btn_返回_Click);
            // 
            // btn_获取新闻链接
            // 
            this.btn_获取新闻链接.Dock = System.Windows.Forms.DockStyle.Right;
            this.btn_获取新闻链接.Location = new System.Drawing.Point(756, 0);
            this.btn_获取新闻链接.Name = "btn_获取新闻链接";
            this.btn_获取新闻链接.Size = new System.Drawing.Size(110, 23);
            this.btn_获取新闻链接.TabIndex = 7;
            this.btn_获取新闻链接.Text = "获取新闻链接";
            this.btn_获取新闻链接.UseVisualStyleBackColor = true;
            this.btn_获取新闻链接.Click += new System.EventHandler(this.btn_获取新闻链接_Click);
            // 
            // listBox1
            // 
            this.listBox1.Dock = System.Windows.Forms.DockStyle.Right;
            this.listBox1.FormattingEnabled = true;
            this.listBox1.HorizontalScrollbar = true;
            this.listBox1.ItemHeight = 12;
            this.listBox1.Location = new System.Drawing.Point(612, 23);
            this.listBox1.Name = "listBox1";
            this.listBox1.ScrollAlwaysVisible = true;
            this.listBox1.Size = new System.Drawing.Size(254, 405);
            this.listBox1.TabIndex = 1;
            this.listBox1.MouseClick += new System.Windows.Forms.MouseEventHandler(this.listBox1_MouseClick);
            // 
            // webBrowser_Tencent
            // 
            this.webBrowser_Tencent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.webBrowser_Tencent.Location = new System.Drawing.Point(0, 23);
            this.webBrowser_Tencent.MinimumSize = new System.Drawing.Size(20, 20);
            this.webBrowser_Tencent.Name = "webBrowser_Tencent";
            this.webBrowser_Tencent.Size = new System.Drawing.Size(612, 405);
            this.webBrowser_Tencent.TabIndex = 2;
            this.webBrowser_Tencent.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.webBrowser_Tencent_DocumentCompleted);
            this.webBrowser_Tencent.NewWindow += new System.ComponentModel.CancelEventHandler(this.webBrowser_Tencent_NewWindow);
            // 
            // Tencent
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(866, 428);
            this.Controls.Add(this.webBrowser_Tencent);
            this.Controls.Add(this.listBox1);
            this.Controls.Add(this.panel1);
            this.Name = "Tencent";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Tencent";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.Tencent_Load);
            this.panel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.ListBox listBox1;
        private System.Windows.Forms.WebBrowser webBrowser_Tencent;
        private System.Windows.Forms.Button btn_发布当前链接到微博;
        private System.Windows.Forms.Button btn_发布微博;
        private System.Windows.Forms.Button btn_首页;
        private System.Windows.Forms.Button btn_停止获取新闻链接;
        private System.Windows.Forms.Button btn_返回;
        private System.Windows.Forms.Button btn_获取新闻链接;
        private System.Windows.Forms.Timer timer1;
    }
}