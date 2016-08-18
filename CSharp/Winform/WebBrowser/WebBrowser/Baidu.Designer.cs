namespace WebBrowser
{
    partial class Baidu
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
            this.components = new System.ComponentModel.Container();
            this.btn_返回 = new System.Windows.Forms.Button();
            this.btn_获取新闻链接 = new System.Windows.Forms.Button();
            this.panel1 = new System.Windows.Forms.Panel();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.btn_JavaScriptTest = new System.Windows.Forms.Button();
            this.btn_发布当前链接到微博 = new System.Windows.Forms.Button();
            this.btn_发布微博 = new System.Windows.Forms.Button();
            this.btn_首页 = new System.Windows.Forms.Button();
            this.btn_停止获取新闻链接 = new System.Windows.Forms.Button();
            this.listBox1 = new System.Windows.Forms.ListBox();
            this.webBrowser1 = new System.Windows.Forms.WebBrowser();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.btn_腾讯 = new System.Windows.Forms.Button();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // btn_返回
            // 
            this.btn_返回.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_返回.Location = new System.Drawing.Point(0, 0);
            this.btn_返回.Name = "btn_返回";
            this.btn_返回.Size = new System.Drawing.Size(51, 22);
            this.btn_返回.TabIndex = 3;
            this.btn_返回.Text = "返回";
            this.btn_返回.UseVisualStyleBackColor = true;
            this.btn_返回.Click += new System.EventHandler(this.btn_返回_Click);
            // 
            // btn_获取新闻链接
            // 
            this.btn_获取新闻链接.Dock = System.Windows.Forms.DockStyle.Right;
            this.btn_获取新闻链接.Location = new System.Drawing.Point(674, 0);
            this.btn_获取新闻链接.Name = "btn_获取新闻链接";
            this.btn_获取新闻链接.Size = new System.Drawing.Size(110, 22);
            this.btn_获取新闻链接.TabIndex = 1;
            this.btn_获取新闻链接.Text = "获取新闻链接";
            this.btn_获取新闻链接.UseVisualStyleBackColor = true;
            this.btn_获取新闻链接.Click += new System.EventHandler(this.btn_获取新闻链接_Click);
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.btn_腾讯);
            this.panel1.Controls.Add(this.textBox1);
            this.panel1.Controls.Add(this.btn_JavaScriptTest);
            this.panel1.Controls.Add(this.btn_发布当前链接到微博);
            this.panel1.Controls.Add(this.btn_发布微博);
            this.panel1.Controls.Add(this.btn_首页);
            this.panel1.Controls.Add(this.btn_停止获取新闻链接);
            this.panel1.Controls.Add(this.btn_返回);
            this.panel1.Controls.Add(this.btn_获取新闻链接);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(784, 22);
            this.panel1.TabIndex = 4;
            // 
            // textBox1
            // 
            this.textBox1.Dock = System.Windows.Forms.DockStyle.Left;
            this.textBox1.Location = new System.Drawing.Point(403, 0);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(82, 21);
            this.textBox1.TabIndex = 8;
            // 
            // btn_JavaScriptTest
            // 
            this.btn_JavaScriptTest.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_JavaScriptTest.Location = new System.Drawing.Point(301, 0);
            this.btn_JavaScriptTest.Name = "btn_JavaScriptTest";
            this.btn_JavaScriptTest.Size = new System.Drawing.Size(102, 22);
            this.btn_JavaScriptTest.TabIndex = 7;
            this.btn_JavaScriptTest.Text = "JavaScriptTest";
            this.btn_JavaScriptTest.UseVisualStyleBackColor = true;
            this.btn_JavaScriptTest.Click += new System.EventHandler(this.btn_JavaScriptTest_Click);
            // 
            // btn_发布当前链接到微博
            // 
            this.btn_发布当前链接到微博.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_发布当前链接到微博.Location = new System.Drawing.Point(176, 0);
            this.btn_发布当前链接到微博.Name = "btn_发布当前链接到微博";
            this.btn_发布当前链接到微博.Size = new System.Drawing.Size(125, 22);
            this.btn_发布当前链接到微博.TabIndex = 6;
            this.btn_发布当前链接到微博.Text = "发布当前链接到微博";
            this.btn_发布当前链接到微博.UseVisualStyleBackColor = true;
            this.btn_发布当前链接到微博.Click += new System.EventHandler(this.btn_发布当前链接到微博_Click);
            // 
            // btn_发布微博
            // 
            this.btn_发布微博.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_发布微博.Location = new System.Drawing.Point(107, 0);
            this.btn_发布微博.Name = "btn_发布微博";
            this.btn_发布微博.Size = new System.Drawing.Size(69, 22);
            this.btn_发布微博.TabIndex = 5;
            this.btn_发布微博.Text = "发布微博";
            this.btn_发布微博.UseVisualStyleBackColor = true;
            this.btn_发布微博.Click += new System.EventHandler(this.btn_发布微博_Click);
            // 
            // btn_首页
            // 
            this.btn_首页.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_首页.Location = new System.Drawing.Point(51, 0);
            this.btn_首页.Name = "btn_首页";
            this.btn_首页.Size = new System.Drawing.Size(56, 22);
            this.btn_首页.TabIndex = 4;
            this.btn_首页.Text = "首页";
            this.btn_首页.UseVisualStyleBackColor = true;
            this.btn_首页.Click += new System.EventHandler(this.btn_首页_Click);
            // 
            // btn_停止获取新闻链接
            // 
            this.btn_停止获取新闻链接.Dock = System.Windows.Forms.DockStyle.Right;
            this.btn_停止获取新闻链接.Location = new System.Drawing.Point(564, 0);
            this.btn_停止获取新闻链接.Name = "btn_停止获取新闻链接";
            this.btn_停止获取新闻链接.Size = new System.Drawing.Size(110, 22);
            this.btn_停止获取新闻链接.TabIndex = 2;
            this.btn_停止获取新闻链接.Text = "停止获取新闻链接";
            this.btn_停止获取新闻链接.UseVisualStyleBackColor = true;
            this.btn_停止获取新闻链接.Click += new System.EventHandler(this.btn_停止获取新闻链接_Click);
            // 
            // listBox1
            // 
            this.listBox1.Dock = System.Windows.Forms.DockStyle.Right;
            this.listBox1.FormattingEnabled = true;
            this.listBox1.ItemHeight = 12;
            this.listBox1.Location = new System.Drawing.Point(442, 22);
            this.listBox1.Name = "listBox1";
            this.listBox1.Size = new System.Drawing.Size(342, 439);
            this.listBox1.TabIndex = 4;
            this.listBox1.MouseClick += new System.Windows.Forms.MouseEventHandler(this.listBox1_MouseClick);
            // 
            // webBrowser1
            // 
            this.webBrowser1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.webBrowser1.Location = new System.Drawing.Point(0, 22);
            this.webBrowser1.MinimumSize = new System.Drawing.Size(20, 20);
            this.webBrowser1.Name = "webBrowser1";
            this.webBrowser1.Size = new System.Drawing.Size(442, 439);
            this.webBrowser1.TabIndex = 5;
            this.webBrowser1.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.webBrowser_DocumentCompleted);
            this.webBrowser1.NewWindow += new System.ComponentModel.CancelEventHandler(this.webBrowser1_NewWindow);
            // 
            // timer1
            // 
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // btn_腾讯
            // 
            this.btn_腾讯.Dock = System.Windows.Forms.DockStyle.Left;
            this.btn_腾讯.Location = new System.Drawing.Point(485, 0);
            this.btn_腾讯.Name = "btn_腾讯";
            this.btn_腾讯.Size = new System.Drawing.Size(56, 22);
            this.btn_腾讯.TabIndex = 9;
            this.btn_腾讯.Text = "腾讯";
            this.btn_腾讯.UseVisualStyleBackColor = true;
            this.btn_腾讯.Click += new System.EventHandler(this.btn_腾讯_Click);
            // 
            // Baidu
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(784, 461);
            this.Controls.Add(this.webBrowser1);
            this.Controls.Add(this.listBox1);
            this.Controls.Add(this.panel1);
            this.Name = "Baidu";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Baidu";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.Load += new System.EventHandler(this.Baidu_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Button btn_返回;
        private System.Windows.Forms.Button btn_获取新闻链接;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.ListBox listBox1;
        private System.Windows.Forms.WebBrowser webBrowser1;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.Button btn_停止获取新闻链接;
        private System.Windows.Forms.Button btn_首页;
        private System.Windows.Forms.Button btn_发布微博;
        private System.Windows.Forms.Button btn_发布当前链接到微博;
        private System.Windows.Forms.Button btn_JavaScriptTest;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Button btn_腾讯;
    }
}

