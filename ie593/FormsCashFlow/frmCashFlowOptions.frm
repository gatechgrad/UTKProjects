VERSION 5.00
Begin VB.Form frmCashFlowOptions 
   Caption         =   "Cash Flow Options"
   ClientHeight    =   5910
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   6525
   LinkTopic       =   "Form1"
   ScaleHeight     =   5910
   ScaleWidth      =   6525
   StartUpPosition =   1  'CenterOwner
   Begin VB.CommandButton butCancel 
      Caption         =   "Cancel"
      Height          =   495
      Left            =   5040
      TabIndex        =   8
      Top             =   5160
      Width           =   1215
   End
   Begin VB.CommandButton butOK 
      Caption         =   "OK"
      Height          =   495
      Left            =   3480
      TabIndex        =   7
      Top             =   5160
      Width           =   1335
   End
   Begin VB.Frame Frame1 
      Caption         =   "Units"
      Height          =   1095
      Left            =   120
      TabIndex        =   5
      Top             =   240
      Width           =   5415
      Begin VB.TextBox txtUnits 
         Height          =   375
         Left            =   360
         TabIndex        =   6
         Top             =   480
         Width           =   1815
      End
   End
   Begin VB.Frame Frame2 
      Caption         =   "Cash Flow Graph"
      Height          =   2775
      Left            =   240
      TabIndex        =   0
      Top             =   1800
      Width           =   5295
      Begin VB.CheckBox Check2 
         Caption         =   "Show Periods"
         Height          =   375
         Left            =   120
         TabIndex        =   4
         Top             =   840
         Value           =   1  'Checked
         Width           =   1935
      End
      Begin VB.CheckBox Check1 
         Caption         =   "Show Values"
         Height          =   375
         Left            =   120
         TabIndex        =   3
         Top             =   360
         Value           =   1  'Checked
         Width           =   1815
      End
      Begin VB.OptionButton Option1 
         Caption         =   "Black and White"
         Height          =   375
         Left            =   120
         TabIndex        =   2
         Top             =   1560
         Width           =   2055
      End
      Begin VB.OptionButton Option2 
         Caption         =   "Color"
         Height          =   375
         Left            =   120
         TabIndex        =   1
         Top             =   2040
         Value           =   -1  'True
         Width           =   1935
      End
   End
End
Attribute VB_Name = "frmCashFlowOptions"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Private Sub butCancel_Click()
'    frmCashFlowOptions.Hide
    Unload frmCashFlowOptions

End Sub

Private Sub butOK_Click()
    'frmCashFlowOptions.Hide
    Unload frmCashFlowOptions
End Sub

Private Sub Form_Load()
    txtUnits.Text = "1"
End Sub
