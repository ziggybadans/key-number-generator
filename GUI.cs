using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Octokit;
using System.Net;

namespace KeyNumberGenerator
{
    public partial class GUI : Form
    {
        readonly KeyNumberGenerator keyNumberGenerator = new KeyNumberGenerator();
        string version;

        public GUI()
        {
            InitializeComponent();
            
            marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
            durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
            typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
            yearInput.Text = keyNumberGenerator.Load("year");
            writerInput.Text = keyNumberGenerator.Load("writerInitial");
            clientInput.Text = keyNumberGenerator.Load("clientInitial");
            numberOutput.Text = keyNumberGenerator.Load("number");

            keyNumberGenerator.SetMarket(marketInput.Text);
            keyNumberGenerator.SetDuration(int.Parse(durationInput.Text));
            keyNumberGenerator.SetType(typeInput.Text);
            keyNumberGenerator.SetYear(yearInput.Text);
            keyNumberGenerator.SetWriterI(writerInput.Text);
            keyNumberGenerator.SetClientI(clientInput.Text);

            if (!keyNumberGenerator.marketReady)
            {
                marketLabel.BackColor = Color.AntiqueWhite;
            }
            if (!keyNumberGenerator.durationReady)
            {
                durationLabel.BackColor = Color.AntiqueWhite;
            }
            if (!keyNumberGenerator.typeReady)
            {
                typeLabel.BackColor = Color.AntiqueWhite;
            }
            if (!keyNumberGenerator.yearReady || yearInput.Text == "Type year")
            {
                yearLabel.BackColor = Color.AntiqueWhite;
            }
            if (writerInput.Text == "Type name")
            {
                writerLabel.BackColor = Color.AntiqueWhite;
            }
            if (clientInput.Text == "Type client name")
            {
                clientLabel.BackColor = Color.AntiqueWhite;
            }

            DownloadUpdate();
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            System.Diagnostics.Process.Start("https://github.com/ziggybadans/key-number-generator");
        }

        private void generateButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetMarket(marketInput.Text);
            keyNumberGenerator.SetYear(yearInput.Text);
            keyNumberGenerator.SetWriterI(writerInput.Text);
            try
            {
                keyNumberGenerator.SetDuration(int.Parse(durationInput.Text));
            } catch (Exception) { }
            
            keyNumberGenerator.SetType(typeInput.Text);
            keyNumberGenerator.SetClientI(clientInput.Text);

            keyNumberOutput.Text = keyNumberGenerator.Generate();
            numberOutput.Text = keyNumberGenerator.Load("number");

            if (writerInput.Text == "Type name")
            {
                writerLabel.BackColor = Color.AntiqueWhite;
            }
            if (clientInput.Text == "Type client name")
            {
                clientLabel.BackColor = Color.AntiqueWhite;
            }
            if (yearInput.Text == "Type year")
            {
                yearLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void yearInput_Enter(object sender, EventArgs e)
        {
            yearLabel.BackColor = Color.WhiteSmoke;
            if (yearInput.Text == "Type year")
            {
                yearInput.Text = "";
            }
        }

        private void yearInput_Leave(object sender, EventArgs e)
        {
            if (yearInput.Text == "")
            {
                yearInput.Text = "Type year";
            }
            keyNumberGenerator.SetYear(yearInput.Text);
            if (!keyNumberGenerator.yearReady || yearInput.Text == "Type year")
            {
                yearLabel.BackColor = Color.AntiqueWhite;
            } else
            {
                yearLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void writerInput_Enter(object sender, EventArgs e)
        {
            writerLabel.BackColor = Color.WhiteSmoke;
            if (writerInput.Text == "Type name")
            {
                writerInput.Text = "";
            }
        }

        private void writerInput_Leave(object sender, EventArgs e)
        {
            if (writerInput.Text == "")
            {
                writerInput.Text = "Type name";
            }
            keyNumberGenerator.SetWriterI(writerInput.Text);
            if (writerInput.Text == "Type name")
            {
                writerLabel.BackColor = Color.AntiqueWhite;
            }
            else
            {
                writerLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void clientInput_Enter(object sender, EventArgs e)
        {
            clientLabel.BackColor = Color.WhiteSmoke;
            if (clientInput.Text == "Type client name")
            {
                clientInput.Text = "";
            }
        }

        private void clientInput_Leave(object sender, EventArgs e)
        {
            if (clientInput.Text == "")
            {
                clientInput.Text = "Type client name";
            }
            keyNumberGenerator.SetClientI(clientInput.Text);
            if (clientInput.Text == "Type client name")
            {
                clientLabel.BackColor = Color.AntiqueWhite;
            }
            else
            {
                clientLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void reloadToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (marketInput.Text == "NULL")
            {
                marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
                marketInput.DropDownStyle = ComboBoxStyle.DropDownList;
                marketLabel.BackColor = Color.WhiteSmoke;
            }
            else
            {
                marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
            }
            if (durationInput.Text == "NULL")
            {
                durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
                durationInput.DropDownStyle = ComboBoxStyle.DropDownList;
                durationLabel.BackColor = Color.WhiteSmoke;
            }
            else
            {
                durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
            }
            if (typeInput.Text == "NULL")
            {
                typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
                typeInput.DropDownStyle = ComboBoxStyle.DropDownList;
                typeLabel.BackColor = Color.WhiteSmoke;
            }
            else
            {
                typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
            }

            yearInput.Text = keyNumberGenerator.Load("year");
            writerInput.Text = keyNumberGenerator.Load("writerInitial");
            clientInput.Text = keyNumberGenerator.Load("clientInitial");
            numberOutput.Text = keyNumberGenerator.Load("number");
        }

        private void marketPropertiesButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetMarket(marketInput.SelectedItem.ToString());
            keyNumberGenerator.Save("market");
        }

        private void durationPropertiesButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetDuration(int.Parse(durationInput.SelectedItem.ToString()));
            keyNumberGenerator.Save("duration");
        }

        private void typePropertiesButton_Click(object sender, EventArgs e)
        {
            keyNumberGenerator.SetType(typeInput.SelectedItem.ToString());
            keyNumberGenerator.Save("type");
        }

        private void yearPropertiesButton_Click(object sender, EventArgs e)
        {
            if (yearInput.Text == "Type year")
            {
                keyNumberGenerator.SetYear(yearInput.Text);
                Properties.Settings.Default.year = yearInput.Text;
            }
            else
            {
                keyNumberGenerator.SetYear(yearInput.Text);
                keyNumberGenerator.Save("year");
            }
        }

        private void writerPropertiesButton_Click(object sender, EventArgs e)
        {
            if (writerInput.Text == "Type name")
            {
                keyNumberGenerator.SetWriterI(writerInput.Text);
                Properties.Settings.Default.writerInitial = writerInput.Text;
            }
            else
            {
                keyNumberGenerator.SetWriterI(writerInput.Text);
                keyNumberGenerator.Save("writerInitial");
            }
        }

        private void clientPropertiesButton_Click(object sender, EventArgs e)
        {
            if (clientInput.Text == "Type year")
            {
                keyNumberGenerator.SetClientI(clientInput.Text);
                Properties.Settings.Default.clientInitial = clientInput.Text;
            }
            else
            {
                keyNumberGenerator.SetClientI(clientInput.Text);
                keyNumberGenerator.Save("clientInitial");
            }
        }

        private void marketNullButton_Click(object sender, EventArgs e)
        {
            marketInput.DropDownStyle = ComboBoxStyle.DropDown;
            marketInput.Text = "NULL";
            keyNumberGenerator.SetMarket(null);
            marketLabel.BackColor = Color.AntiqueWhite;
        }

        private void durationNullButton_Click(object sender, EventArgs e)
        {
            durationInput.DropDownStyle = ComboBoxStyle.DropDown;
            durationInput.Text = "NULL";
            keyNumberGenerator.SetDuration(-1);
            durationLabel.BackColor = Color.AntiqueWhite;
        }

        private void typeNullButton_Click(object sender, EventArgs e)
        {
            typeInput.DropDownStyle = ComboBoxStyle.DropDown;
            typeInput.Text = "NULL";
            keyNumberGenerator.SetType(null);
            typeLabel.BackColor = Color.AntiqueWhite;
        }

        private void marketInput_Enter(object sender, EventArgs e)
        {
            if (marketInput.Text == "NULL")
            {
                marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
                marketInput.DropDownStyle = ComboBoxStyle.DropDownList;
                marketLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void durationInput_Enter(object sender, EventArgs e)
        {
            if (durationInput.Text == "NULL")
            {
                durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
                durationInput.DropDownStyle = ComboBoxStyle.DropDownList;
                durationLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void typeInput_Enter(object sender, EventArgs e)
        {
            if (typeInput.Text == "NULL")
            {
                typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
                typeInput.DropDownStyle = ComboBoxStyle.DropDownList;
                typeLabel.BackColor = Color.WhiteSmoke;
            }
        }

        private void copyButton_Click(object sender, EventArgs e)
        {
            System.Windows.Forms.Clipboard.SetText(keyNumberGenerator.keyNumber);
        }

        private void marketInput_Leave(object sender, EventArgs e)
        {
            keyNumberGenerator.SetMarket(marketInput.Text);
            if (!keyNumberGenerator.marketReady)
            {
                marketLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void durationInput_Leave(object sender, EventArgs e)
        {
            keyNumberGenerator.SetDuration(int.Parse(durationInput.Text));
            if (!keyNumberGenerator.durationReady)
            {
                durationLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void typeInput_Leave(object sender, EventArgs e)
        {
            keyNumberGenerator.SetType(typeInput.Text);
            if (!keyNumberGenerator.typeReady)
            {
                typeLabel.BackColor = Color.AntiqueWhite;
            }
        }

        private void yearInput_TextChanged(object sender, EventArgs e)
        {
            if (yearInput.Text != "Type year")
            {
                yearLabel.BackColor = Color.WhiteSmoke;
                if (yearInput.TextLength != 0)
                {
                    for (int i = 0; i < yearInput.TextLength; i++)
                    {
                        if (!char.IsDigit(yearInput.Text[i]))
                        {
                            yearInput.Text = yearInput.Text.Remove(i, 1);
                            yearLabel.BackColor = Color.AntiqueWhite;
                        }
                    }
                }
            }
        }

        private void keyNumberOutput_TextChanged(object sender, EventArgs e)
        {
            keyNumberLabel.BackColor = Color.WhiteSmoke;
            if (keyNumberOutput.TextLength != 0)
            {
                for (int i = 0; i < keyNumberOutput.TextLength; i++)
                {
                    if (keyNumberOutput.Text[i] != '-' && !char.IsLetterOrDigit(keyNumberOutput.Text[i])) 
                    {
                        keyNumberOutput.Text = keyNumberOutput.Text.Remove(i, 1);
                        keyNumberLabel.BackColor = Color.AntiqueWhite;
                    }
                }
            }
        }

        private void keyNumberOutput_Leave(object sender, EventArgs e)
        {
            keyNumberLabel.BackColor = Color.WhiteSmoke;
        }

        private void advancedToolStripMenuItem_Click(object sender, EventArgs e)
        {
            EditProperties popup = new EditProperties();
            DialogResult dialogResult = popup.ShowDialog();
            if (dialogResult == DialogResult.OK)
            {
                if (marketInput.Text == "NULL")
                {
                    marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
                    marketInput.DropDownStyle = ComboBoxStyle.DropDownList;
                    marketLabel.BackColor = Color.WhiteSmoke;
                }
                else
                {
                    marketInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.markets, keyNumberGenerator.Load("market"));
                }
                if (durationInput.Text == "NULL")
                {
                    durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
                    durationInput.DropDownStyle = ComboBoxStyle.DropDownList;
                    durationLabel.BackColor = Color.WhiteSmoke;
                }
                else
                {
                    durationInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.durations, int.Parse(keyNumberGenerator.Load("duration")));
                }
                if (typeInput.Text == "NULL")
                {
                    typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
                    typeInput.DropDownStyle = ComboBoxStyle.DropDownList;
                    typeLabel.BackColor = Color.WhiteSmoke;
                }
                else
                {
                    typeInput.SelectedIndex = Array.IndexOf(KeyNumberGenerator.types, keyNumberGenerator.Load("type"));
                }

                yearInput.Text = keyNumberGenerator.Load("year");
                writerInput.Text = keyNumberGenerator.Load("writerInitial");
                clientInput.Text = keyNumberGenerator.Load("clientInitial");
                numberOutput.Text = keyNumberGenerator.Load("number");
            }
            else if (dialogResult == DialogResult.Cancel) { }
            popup.Dispose();
        }
        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AboutBox popup = new AboutBox();
            DialogResult dialogResult = popup.ShowDialog();

            if (dialogResult == DialogResult.OK)
            {

            }
            popup.Dispose();
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
            updateButton.Visible = true;
        }

        public async void DownloadUpdate()
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
        }

        private void updateButton_Click(object sender, EventArgs e)
        {
            string message = "Version " + version + 
                " was downloaded to " + System.IO.Directory.GetCurrentDirectory() + "\\download\\" + 
                ". Close the program and replace the old .exe file with the new one.";
            string caption = "Downloaded update";
            MessageBoxButtons buttons = MessageBoxButtons.OK;
            MessageBox.Show(message, caption, buttons);
            
        }
    }
}
