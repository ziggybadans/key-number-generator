using Octokit;
using System;
using System.ComponentModel;
using System.Drawing;
using System.Net;
using System.Reflection;
using System.Windows.Forms;

namespace KeyNumberGenerator
{
    partial class AboutBox : Form
    {
        string version;

        public AboutBox()
        {
            InitializeComponent();
            this.Text = String.Format("About {0}", AssemblyTitle);
            this.labelProductName.Text = AssemblyProduct;
            this.labelVersion.Text = String.Format("Version {0}", AssemblyVersion);
            this.labelCopyright.Text = AssemblyCopyright;

            Version();
        }

        async void Version()
        {
            labelVersion.Text = "Version " + Properties.Settings.Default.Version;

            try
            {
                var client = new GitHubClient(new ProductHeaderValue("key-number-generator"));
                var releases = await client.Repository.Release.GetAll("ziggybadans", "key-number-generator");
                var latest = releases[0];
                string version = latest.TagName;
                Console.WriteLine("Latest version is: " + version);

                if (version != Properties.Settings.Default.Version)
                {
                    label1.Text = "New Update Available!";
                    label2.Visible = true;
                    label2.Text = "Version " + version;
                    button2.Visible = true;
                }
                else
                {
                    label1.Text = "Up-to-date!";
                }
            }
            catch (Exception)
            {

            }
        }

        public async void DownloadUpdate()
        {
            try
            {
                var client = new GitHubClient(new ProductHeaderValue("key-number-generator"));
                var releases = await client.Repository.Release.GetAll("ziggybadans", "key-number-generator");
                var latest = releases[0];
                version = latest.TagName;
                Console.WriteLine("Latest version is: " + version);

                if (version != Properties.Settings.Default.Version)
                {
                    progressBar1.Visible = true;
                    if (!System.IO.Directory.Exists(System.IO.Directory.GetCurrentDirectory() + "\\download\\"))
                    {
                        System.IO.Directory.CreateDirectory(System.IO.Directory.GetCurrentDirectory() + "\\download\\");
                    }
                    using (WebClient wc = new WebClient())
                    {
                        Console.WriteLine("URI: " + latest.Assets[1].BrowserDownloadUrl);
                        wc.DownloadProgressChanged += new DownloadProgressChangedEventHandler(this.wc_DownloadProgressChanged);
                        Console.WriteLine("Path: " + System.IO.Directory.GetCurrentDirectory() + "\\download\\" + latest.Assets[1].Name);
                        wc.DownloadFileAsync(
                            new System.Uri(latest.Assets[1].BrowserDownloadUrl),
                            System.IO.Directory.GetCurrentDirectory() + "\\download\\" + latest.Assets[1].Name
                            );
                        wc.DownloadFileCompleted += new AsyncCompletedEventHandler(this.wc_DownloadDataCompleted);
                    }
                }
                else
                {
                    label1.BackColor = Color.AliceBlue;
                }
            }
            catch (Exception)
            {

            }
        }
        #region Assembly Attribute Accessors

        public string AssemblyTitle
        {
            get
            {
                object[] attributes = Assembly.GetExecutingAssembly().GetCustomAttributes(typeof(AssemblyTitleAttribute), false);
                if (attributes.Length > 0)
                {
                    AssemblyTitleAttribute titleAttribute = (AssemblyTitleAttribute)attributes[0];
                    if (titleAttribute.Title != "")
                    {
                        return titleAttribute.Title;
                    }
                }
                return System.IO.Path.GetFileNameWithoutExtension(Assembly.GetExecutingAssembly().CodeBase);
            }
        }

        public string AssemblyVersion
        {
            get
            {
                return Assembly.GetExecutingAssembly().GetName().Version.ToString();
            }
        }

        public string AssemblyDescription
        {
            get
            {
                object[] attributes = Assembly.GetExecutingAssembly().GetCustomAttributes(typeof(AssemblyDescriptionAttribute), false);
                if (attributes.Length == 0)
                {
                    return "";
                }
                return ((AssemblyDescriptionAttribute)attributes[0]).Description;
            }
        }

        public string AssemblyProduct
        {
            get
            {
                object[] attributes = Assembly.GetExecutingAssembly().GetCustomAttributes(typeof(AssemblyProductAttribute), false);
                if (attributes.Length == 0)
                {
                    return "";
                }
                return ((AssemblyProductAttribute)attributes[0]).Product;
            }
        }

        public string AssemblyCopyright
        {
            get
            {
                object[] attributes = Assembly.GetExecutingAssembly().GetCustomAttributes(typeof(AssemblyCopyrightAttribute), false);
                if (attributes.Length == 0)
                {
                    return "";
                }
                return ((AssemblyCopyrightAttribute)attributes[0]).Copyright;
            }
        }

        public string AssemblyCompany
        {
            get
            {
                object[] attributes = Assembly.GetExecutingAssembly().GetCustomAttributes(typeof(AssemblyCompanyAttribute), false);
                if (attributes.Length == 0)
                {
                    return "";
                }
                return ((AssemblyCompanyAttribute)attributes[0]).Company;
            }
        }
        #endregion

        private void button1_Click(object sender, EventArgs e)
        {
            Version();
        }

        void wc_DownloadProgressChanged(object sender, DownloadProgressChangedEventArgs e)
        {
            Console.WriteLine("Downloading latest version...");
            progressBar1.Value = e.ProgressPercentage;
        }

        void wc_DownloadDataCompleted(object sender, AsyncCompletedEventArgs e)
        {
            Console.WriteLine("Downloading succeeded!");
            progressBar1.Visible = false;
            button2.Visible = true;

            string message = "Version " + version +
                " was downloaded to " + System.IO.Directory.GetCurrentDirectory() + "\\download\\" +
                ". Close the program and replace the old .exe file with the new one.";
            string caption = "Downloaded update";
            MessageBoxButtons buttons = MessageBoxButtons.OK;
            MessageBox.Show(message, caption, buttons);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            DownloadUpdate();
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            System.Diagnostics.Process.Start("https://github.com/ziggybadans/key-number-generator");
        }
    }
}
