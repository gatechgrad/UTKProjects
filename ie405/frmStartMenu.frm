VERSION 5.00
Begin VB.Form frmStartMenu 
   BackColor       =   &H00E0E0E0&
   Caption         =   "Replacement Analysis"
   ClientHeight    =   3960
   ClientLeft      =   165
   ClientTop       =   555
   ClientWidth     =   6195
   LinkTopic       =   "Form1"
   ScaleHeight     =   3960
   ScaleWidth      =   6195
   StartUpPosition =   2  'CenterScreen
   Begin VB.CommandButton Command2 
      Caption         =   "Study Period Specified"
      Height          =   615
      Left            =   1200
      TabIndex        =   2
      Top             =   2880
      Width           =   3615
   End
   Begin VB.CommandButton Command1 
      Caption         =   "Study Period Not Specified (ESL)"
      Height          =   615
      Left            =   1200
      TabIndex        =   1
      Top             =   1920
      Width           =   3615
   End
   Begin VB.Label Label2 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Please choose the type of replacement analysis problem:"
      ForeColor       =   &H00000000&
      Height          =   375
      Left            =   960
      TabIndex        =   3
      Top             =   1200
      Width           =   4215
   End
   Begin VB.Label Label1 
      BackColor       =   &H00E0E0E0&
      Caption         =   "Replacement Analysis Project"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   18
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00000000&
      Height          =   615
      Left            =   600
      TabIndex        =   0
      Top             =   240
      Width           =   5055
   End
   Begin VB.Menu file 
      Caption         =   "File"
      Begin VB.Menu exit 
         Caption         =   "Exit"
      End
   End
   Begin VB.Menu ref 
      Caption         =   "Reference"
      Begin VB.Menu rsad 
         Caption         =   "Replacement Study Approaches Diagram"
      End
      Begin VB.Menu cifc 
         Caption         =   "Compound Interest Factor Calculator"
      End
   End
   Begin VB.Menu help 
      Caption         =   "Help"
      Begin VB.Menu about 
         Caption         =   "About"
      End
   End
End
Attribute VB_Name = "frmStartMenu"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub about_Click()
        frmAbout.Show vbModal, Me
End Sub

Private Sub cifc_Click()
    frmCIFCal.Show vbModal, Me
End Sub

Private Sub Command1_Click()
    MainForm.Show vbModal, Me
End Sub

Private Sub Command2_Click()
    frmStudyPeriodSpecified.Show vbModal, Me
End Sub

Private Sub exit_Click()
    Unload Me
End Sub

Private Sub rsad_Click()
    frmReplacementStudyApproaches.Show vbModal, Me
End Sub
